<?php

namespace App\Controller;

use App\Entity\Reactioncours;
use App\Form\ReactioncoursType;
use App\Repository\ReactioncoursRepository;
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
        if ($this->isCsrfTokenValid('delete'.$reactioncour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reactioncour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reactioncours_index');
    }
}
