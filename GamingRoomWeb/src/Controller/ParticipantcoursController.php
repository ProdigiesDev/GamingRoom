<?php

namespace App\Controller;

use App\Entity\Participantcours;
use App\Form\ParticipantcoursType;
use App\Repository\ParticipantcoursRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/participantcours")
 */
class ParticipantcoursController extends AbstractController
{
    /**
     * @Route("/", name="participantcours_index", methods={"GET"})
     */
    public function index(ParticipantcoursRepository $participantcoursRepository): Response
    {
        return $this->render('participantcours/index.html.twig', [
            'participantcours' => $participantcoursRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="participantcours_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $participantcour = new Participantcours();
        $form = $this->createForm(ParticipantcoursType::class, $participantcour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($participantcour);
            $entityManager->flush();

            return $this->redirectToRoute('participantcours_index');
        }

        return $this->render('participantcours/new.html.twig', [
            'participantcour' => $participantcour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="participantcours_show", methods={"GET"})
     */
    public function show(Participantcours $participantcour): Response
    {
        return $this->render('participantcours/show.html.twig', [
            'participantcour' => $participantcour,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="participantcours_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Participantcours $participantcour): Response
    {
        $form = $this->createForm(ParticipantcoursType::class, $participantcour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('participantcours_index');
        }

        return $this->render('participantcours/edit.html.twig', [
            'participantcour' => $participantcour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="participantcours_delete", methods={"POST"})
     */
    public function delete(Request $request, Participantcours $participantcour): Response
    {
        if ($this->isCsrfTokenValid('delete'.$participantcour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($participantcour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('participantcours_index');
    }
}
