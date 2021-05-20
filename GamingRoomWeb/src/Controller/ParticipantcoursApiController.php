<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Entity\Participantcours;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ParticipantcoursApiController extends AbstractController
{
    /**
     * @Route("/participantcours/api", name="participantcours_api")
     */
    public function index(): Response
    {
        return $this->render('participantcours_api/index.html.twig', [
            'controller_name' => 'ParticipantcoursApiController',
        ]);
    }


    /**
     * @Route("/new", name="participantcours_new")
     */
    public function new($id): Response
    {
        $user = $this->security->getUser();
        if (!$user) {
            return $this->redirect("/login");
        }
        $entityManager = $this->getDoctrine()->getManager();
        $e = $this->getDoctrine()->getRepository(Cour::class)->find($id);
        $m = $user;

        if ((sizeof($this->getDoctrine()->getRepository(Participantcours::class)->findOneByME($e, $m))) <= 0) {
            $participant = new Participantcours();
            $participant->setCour($e);
            $participant->setMembre($m);


            // tell Doctrine you want to (eventually) save the Product (no queries yet)
            $entityManager->persist($participant);
            $entityManager->flush();
            return new Response('Participant inscrit Ajouté');

        }
    }
}
