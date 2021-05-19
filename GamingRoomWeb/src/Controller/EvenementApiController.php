<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\Membre;
use App\Entity\Participant;
use App\Entity\Reactionev;
use App\Form\EvenementType;
use App\Form\ReactionevType;
use App\Repository\EvenementRepository;
use App\Repository\ParticipantRepository;
use App\Repository\ReactionevRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\Security\Core\Security;

use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class EvenementApiController extends AbstractController
{
    /**
     * @Route("/evenement/api", name="evenement_api")
     */
    public function index(): Response
    {
        return $this->render('evenement_api/index.html.twig', [
            'controller_name' => 'EvenementApiController',
        ]);
    }

    /**
     * @Route("/evenement/api/liste", name="evenement_liste_api", methods={"GET"})
     */
    public function getEvenements(EvenementRepository $evenementRepository,Request $request,SerializerInterface $seriInt,NormalizerInterface $nor): Response
    {

        $evenement =$evenementRepository->findAll();
        $evenementJson=$nor->normalize($evenement,'json');
        //dump("aaaaaa",$evenementJson);die;
        return new Response(json_encode($evenementJson));

    }

    /**
     * @Route("/evenement/api/getEvent/{id}", name="evenement_get_api", methods={"GET"})
     */
    public function getEvenement(EvenementRepository $evenementRepository,Request $request, $id,NormalizerInterface $nor): Response
    {

        $evenement =$evenementRepository->find($id);
        $evenementJson=$nor->normalize($evenement,'json');
        //dump("aaaaaa",$evenementJson);die;
        return new Response(json_encode($evenementJson));

    }

    /**
     * @Route("/evenement/api/ifInscrit/{id}/{idM}", name="evenement_ifInscrit_api", methods={"GET"})
     */
    public function ifInscrit(Request $request, $id,$idM,NormalizerInterface $nor): Response
    {
        $e=$this->getDoctrine()->getRepository(Evenement::class)->find($id);
        $m=$this->getDoctrine()->getRepository(Membre::class)->find($idM);
        $part =$this->getDoctrine()->getRepository(Participant::class)->findBy(array("evenement"=>$e,"member"=>$idM));
        if($part)
            $resp=true;
        else
            $resp=false;
        return $this->json(['code'=>200,
            'message'=>$resp],
            200);

    }

    /**
     * @Route("/evenement/api/inscription/{id}/{idM}", name="evenement_inscription_api", methods={"GET"})
     */
    public function inscription(Request $request, $id,$idM,NormalizerInterface $nor): Response
    {
        $e = $this->getDoctrine()->getRepository(Evenement::class)->find($id);

        $m = $this->getDoctrine()->getRepository(Membre::class)->find($idM);
        if((sizeof($this->getDoctrine()->getRepository(Participant::class)->findBy(array('evenement'=>$e))))< $e->getNbremaxParticipant()) {

            $entityManager = $this->getDoctrine()->getManager();
            $participant = new Participant();
            $participant->setEvenement($e);
            $participant->setMember($m);
            $participant->setRound(1);


            $entityManager->persist($participant);
            $entityManager->flush();


        }else {
            if (($this->getDoctrine()->getRepository(Participant::class)->findBy(array('evenement'=>$e)))[0]->getDuel()==null) {
                //Metier: repartition des duels aleatoirment
                $listeParticipants = $this->getDoctrine()->getRepository(Participant::class)->eventParts($e);
                $memberListe = array();
                foreach ($listeParticipants as $value) {
                    array_push($memberListe, $value->getMember());
                }


                $memberListeSize = sizeof($memberListe);
                $randomValues = array();
                $char = 'A';
                $i = 1;

                while ($memberListe) {
                    $randIndex = array_rand($memberListe);
                    $randomElement = $memberListe[$randIndex];
                    $randomValues[$randomElement->getId()] = $char;
                    unset($memberListe[$randIndex]);
                    if ($i % 2 == 0) {
                        $char++;
                    }
                    $i++;
                }

                foreach ($randomValues as $key => $value) {

                    $m = $this->getDoctrine()->getRepository(Membre::class)->find($key);
                    $this->getDoctrine()->getRepository(Participant::class)->repartitionDual($m, $value, $e);

                }
            }
            return $this->json(['code'=>200,
                'message'=>"saturé"],
                200);
        }

        return $this->json(['code'=>200,
            'message'=>"success"],
            200);

    }

    /**
     * @Route("/evenement/api/annulerInscription/{id}/{idM}", name="evenement_annulerInscription_api", methods={"GET"})
     */
    public function annulerInscription(Request $request, $id,$idM,NormalizerInterface $nor): Response
    {
        $e = $this->getDoctrine()->getRepository(Evenement::class)->find($id);

        $m = $this->getDoctrine()->getRepository(Membre::class)->find($idM);
        $participant=($this->getDoctrine()->getRepository(Participant::class)->delete($e,$m));

        return $this->json(['code'=>200,
            'message'=>"success"],
            200);

    }




}
