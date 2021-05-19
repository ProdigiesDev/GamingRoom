<?php

namespace App\Controller;

use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use App\Entity\Reclamation;
use App\Repository\MembreRepository;
use Symfony\Contracts\HttpClient\HttpClientInterface;

/**
* @Route("/api/reclamation")
*/
class ReclamationApiController extends AbstractController
{
    private $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }


    /**
     * @Route("/", name="rec_api",methods={"GET"})
     */
    public function index(ReclamationRepository $recRep,NormalizerInterface $nor): Response
    {
        $rec=$recRep->findAll();
        $recJson=$nor->normalize($rec,'json');

        return new Response(json_encode($recJson));
    }

    /**
     * @Route("/delete/{id}", name="rec_deletebyid",methods={"GET"})
     */
    public function deleteById(Reclamation $rec): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($rec);
        $entityManager->flush();
        
        return $this->json(['message'=>'reclamation deleted'],200);
    }

    /**
    * @Route("/addreclamation",name="add_rec",methods={"GET"})
    */
    public function add(Request $request,MembreRepository $memRep){

        $en=$this->getDoctrine()->getManager();
        $rec=new Reclamation();
        $rec->setSujet($request->query->get('sujet'));
        $rec->setContenue($request->query->get('contenue'));
        $rec->setMembre($memRep->find($request->query->get('member_id')));

        $en->persist($rec);
        $en->flush();

        return new Response(json_encode($this->json(['code'=>200, 'message'=>'Reclamation ajouter'],200)));
    }

    
    
  
}
