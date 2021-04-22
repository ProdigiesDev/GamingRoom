<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Entity\Membre;
use App\Entity\Reactioncours;
use App\Form\ReactioncoursType;
use App\Repository\ReactioncoursRepository;
use DateTime;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reactioncours")
 */
class ReactioncoursController extends AbstractController
{
    /**
     * @Route("/", name="reactioncours_index", methods={"GET"})
     */
    public function index(ReactioncoursRepository $reactioncoursRepository): Response
    {
        return $this->render('reactioncours/index.html.twig', [
            'reactioncours' => $reactioncoursRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="reactioncours_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $reactioncour = new Reactioncours();
        $form = $this->createForm(ReactioncoursType::class, $reactioncour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reactioncour);
            $entityManager->flush();

            return $this->redirectToRoute('reactioncours_index');
        }

        return $this->render('reactioncours/new.html.twig', [
            'reactioncour' => $reactioncour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="reactioncours_show", methods={"GET"})
     */
    public function show(Reactioncours $reactioncour): Response
    {
        return $this->render('reactioncours/show.html.twig', [
            'reactioncour' => $reactioncour,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="reactioncours_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reactioncours $reactioncour): Response
    {
        $form = $this->createForm(ReactioncoursType::class, $reactioncour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reactioncours_index');
        }

        return $this->render('reactioncours/edit.html.twig', [
            'reactioncour' => $reactioncour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="reactioncours_delete", methods={"POST"})
     */
    public function delete(Request $request, Reactioncours $reactioncour): Response
    {
        if ($this->isCsrfTokenValid('delete' . $reactioncour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reactioncour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reactioncours_index');
    }


    /**
     * @Route("/reaction/ajout", name="reaction")
     */
    public function like(Request $request)
    {
        $idMembre = $this->getDoctrine()->getRepository(Membre::class)->find(8);

        $likeType = (int)$request->get('typeReactioncours');
        $idCour = $request->get('idCour');

        $haveReactioncours = $this->getDoctrine()->getRepository(Reactioncours::class)->findOneBy([
            'membre' => $this->getDoctrine()->getRepository(Membre::class)->find($idMembre),
            'cour' => $this->getDoctrine()->getRepository(Cour::class)->find($idCour)
        ]);

        $this->addReactioncours($haveReactioncours, $likeType, $idMembre, $idCour);


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

    function addReactioncours($haveReactioncours, $typeReactioncours, $membre, $courId)
    {

        if ($haveReactioncours == null) {
            $reactioncours = new Reactioncours();
            $reactioncours->setInteraction($typeReactioncours);
            $cour = $this->getDoctrine()->getManager()->getRepository(Cour::class)->find($courId);

            $reactioncours->setCour($cour);
            $reactioncours->setMembre($membre);

            $date = new DateTime('now', new \DateTimeZone('Africa/Tunis'));
            $reactioncours->setDateCreation($date);


            $repository = $this->getDoctrine()->getManager();
            $repository->persist($reactioncours);
            $repository->flush();

        } else {
            $missionManager = $this->getDoctrine()->getManager();
            $missionManager->remove($haveReactioncours);
            $missionManager->flush();
        }
    }


}
