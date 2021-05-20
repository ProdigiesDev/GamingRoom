<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Reactioncours;
use Symfony\Component\Security\Core\Security;

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
     * @Route("/numberlikedislike", name="numberlikedislike")
     */

    public function like(Request $request)
    {
        $user = $this->security->getUser();
        if(!$user){
            $jsonContent['notFound'] = 404;
            return new Response(json_encode($jsonContent));
        }
        $idMembre = $user;

        $likeType = (int)$request->get('typeReactioncours');
        $idCour = $request->get('idCour');

        $haveReactioncours = $this->getDoctrine()->getRepository(Reactioncours::class)->haveLikeDislike(
            $this->getDoctrine()->getRepository(Cour::class)->find($idCour),
            $this->getDoctrine()->getRepository(Membre::class)->find($idMembre)
        );


        $this->addReactioncours($haveReactioncours, $likeType, "NULL", $idMembre, $idCour);


        $nombreObjets = $this->getDoctrine()->getRepository(Reactioncours::class)->nombreObjets($idCour);
        $nombreReactioncours = $this->getDoctrine()->getRepository(Reactioncours::class)->nombreLikes($idCour);
        if ($nombreReactioncours != 0) {
            $pourcentage = ($nombreReactioncours / $nombreObjets) * 100;
        } else {
            $pourcentage = 0;
        }

        $cour = $this->getDoctrine()->getManager()->getRepository(Cour::class)->find($idCour);
        $cour->setPourcentageLike($pourcentage);

        $repository = $this->getDoctrine()->getManager();
        $repository->persist($cour);
        $repository->flush();

        $jsonContent['nbLike'] = $nombreReactioncours;
        $jsonContent['nbDislike'] = ($nombreObjets - $nombreReactioncours);
        $jsonContent['pourcentage'] = $pourcentage;
        return new Response(json_encode($jsonContent));
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
