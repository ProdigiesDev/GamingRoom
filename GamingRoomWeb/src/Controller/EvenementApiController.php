<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\Membre;
use App\Entity\Participant;
use App\Entity\Reactionev;
use App\Form\EvenementType;
use App\Form\ReactionevType;
use App\Repository\EvenementRepository;
use App\Repository\ReactionevRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\Security\Core\Security;

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
    public function getEvenements(EvenementRepository $evenementRepository,Request $request,SerializerInterface $seriInt): Response
    {

        $evenement =$evenementRepository->findAll();
        $evenementJson=$seriInt->serialize($evenement,'json');
        //dump("aaaaaa",$evenementJson);die;
        return new Response(json_encode($evenementJson));

    }

}
