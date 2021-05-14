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


    /**
     * @Route("/addlikedeslike", name="likedeslike")
     */
    function addReactioncours($haveReactioncours, $typeReactioncours, $contenuCommentaire, $membre, $courId)
    {
        if ($haveReactioncours == null) {
            $reactioncours = new Reactioncours();
            $reactioncours->setInteraction($typeReactioncours);
            $cour = $this->getDoctrine()->getManager()->getRepository(Cour::class)->find($courId);

            $reactioncours->setCommentaire($contenuCommentaire);
            $reactioncours->setCour($cour);
            $reactioncours->setMembre($membre);

            $date = new DateTime('now', new \DateTimeZone('Africa/Tunis'));
            $reactioncours->setDateCreation($date);


            $repository = $this->getDoctrine()->getManager();
            $repository->persist($reactioncours);
            $repository->flush();

        } else {
            foreach ($haveReactioncours as $haveReactioncour) {
                $missionManager = $this->getDoctrine()->getManager();
                $missionManager->remove($haveReactioncour);
                $missionManager->flush();
            }
        }
        return new Response($this->json(['code'=>201, 'message'=>'reaction added'],201));
    }
}
