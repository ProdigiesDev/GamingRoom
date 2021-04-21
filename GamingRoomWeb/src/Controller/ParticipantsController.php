<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\Membre;
use App\Entity\Participant;
use App\Form\EvenementType;
use App\Repository\ParticipantRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class ParticipantsController extends AbstractController
{
    /**
     * @Route("/participants", name="participants")
     */
    public function index(): Response
    {
        return $this->render('participants/index.html.twig', [
            'controller_name' => 'ParticipantsController',
        ]);
    }

    /**
     * @Route("/listerPAdmin", name="listerPAdmin")
     */
    public function listerPAdmin(): Response
    {
        $participant=$this->getDoctrine()->getRepository(Participant::class)->findAll();
        return $this->render('participants/listerPAdmin.html.twig', [
            'participant' => $participant,
        ]);
    }

    /**
     * @Route("/listerP", name="listerP")
     */
    public function listerP(): Response
    {
        $participant=$this->getDoctrine()->getRepository(Participant::class)->findAll();
        return $this->render('participants/listerE.html.twig', [
            'participant' => $participant,
        ]);
    }

    /**
     * @Route("/listerE", name="listerE")
     */
    public function listerE(): Response
    {
        $evenements=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
        $i=0;
        foreach ($evenements as $e){
            $m=$this->getDoctrine()->getRepository(Membre::class)->find(8);
            if((sizeof($this->getDoctrine()->getRepository(Participant::class)->findOneByME($e,$m)))<=0){
                $isParticpant[$i]="Inscription";
            }
            else{
                $isParticpant[$i]="Annuler";
            }
            $i++;

        }
        return $this->render('participants/listerE.html.twig', [
            'evenements' => $evenements,
            'isParticpant'=>$isParticpant,
            'size'=>sizeof($isParticpant)-1,
        ]);
    }

    /**
     * @Route("/new/{id}", name="actionParticipant")
     */
    public function new($id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $e=$this->getDoctrine()->getRepository(Evenement::class)->find($id);
        $m=$this->getDoctrine()->getRepository(Membre::class)->find(8);

        if((sizeof($this->getDoctrine()->getRepository(Participant::class)->findOneByME($e,$m)))<=0){
            $participant = new Participant();
            $participant->setEvenement($e);
            $participant->setMember($m);
            $participant->setRound(1);

            // tell Doctrine you want to (eventually) save the Product (no queries yet)
            $entityManager->persist($participant);
            $entityManager->flush();
        }
        else{
            $participant=($this->getDoctrine()->getRepository(Participant::class)->delete($e,$m));
        }



        // actually executes the queries (i.e. the INSERT query)

        return $this->redirectToRoute('listerE');



    }

    /**
     * @Route("/updateRound/{id}", name="updateRound")
     */
    public function updateRound($id): Response
    {
        $this->getDoctrine()->getRepository(Participant::class)->updateRound($id);



        // actually executes the queries (i.e. the INSERT query)

        return $this->redirectToRoute('evenement_index');



    }

}
