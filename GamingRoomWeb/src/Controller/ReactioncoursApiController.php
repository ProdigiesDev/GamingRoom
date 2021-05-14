<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReactioncoursApiController extends AbstractController
{
    /**
     * @Route("/reactioncours/api", name="reactioncours_api")
     */
    public function index(): Response
    {
        return $this->render('reactioncours_api/index.html.twig', [
            'controller_name' => 'ReactioncoursApiController',
        ]);
    }
}
