<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Form\CourType;
use App\Repository\CourRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;

use Symfony\Component\Filesystem\Filesystem;
use GuzzleHttp\Psr7\UploadedFile;
use Symfony\Component\HttpFoundation\JsonRespImageonse;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/cour")
 */
class CourController extends AbstractController
{
    /**
     * @Route("/", name="cour_index", methods={"GET"})
     */
    public function index(CourRepository $courRepository): Response
    {
        return $this->render('cour/index.html.twig', [
            'cours' => $courRepository->findAll(),
        ]);
    }
    /**
     * @Route("/admincours", name="cour_index_admin", methods={"GET"})
     */
    public function adminindex(CourRepository $courRepository): Response
    {
        return $this->render('cour/indexAdmin.html.twig', [
            'cours' => $courRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="cour_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $cour = new Cour();
        $form = $this->createForm(CourType::class, $cour);//récuperation du formulaire
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagecours')->getData();
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move ($this->getParameter('cours_directory'),$fileName);
            $cour->setImagecours($fileName);
            $cour->setImagecours($fileName);
            //entity managerpermet l’insertion, la mise à jour et la suppression des données de la base de données
            $entityManager = $this->getDoctrine()->getManager();//récupérer l’entity manager
            $entityManager->persist($cour);//pour l‘ajout d’un nouvel objet
            $entityManager->flush();//envoyer la maj à la bd

            return $this->redirectToRoute('cour_index'); //redirection apres l'ajout
        }


        return $this->render('cour/new.html.twig', [ //envoi du form à la page twig
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("admin/new", name="cour_new_admin", methods={"GET","POST"})
     */
    public function newbyadmin(Request $request): Response
    {
        $cour = new Cour();
        $form = $this->createForm(CourType::class, $cour);//récuperation du formulaire
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imagecours')->getData();
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move ($this->getParameter('cours_directory'),$fileName);
            $cour->setImagecours($fileName);
            $cour->setImagecours($fileName);
            //entity managerpermet l’insertion, la mise à jour et la suppression des données de la base de données
            $entityManager = $this->getDoctrine()->getManager();//récupérer l’entity manager
            $entityManager->persist($cour);//pour l‘ajout d’un nouvel objet
            $entityManager->flush();//envoyer la maj à la bd

            return $this->redirectToRoute('cour_index'); //redirection apres l'ajout
        }


        return $this->render('cour/newadmin.html.twig', [ //envoi du form à la page twig
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cour_show", methods={"GET"})
     */
    public function show(Cour $cour): Response
    {
        return $this->render('cour/show.html.twig', [
            'cour' => $cour,
        ]);
    }

    /**
     * @Route("showadmin/{id}", name="cour_show_admin", methods={"GET"})
     */
    public function showforadmin(Cour $cour): Response
    {
        return $this->render('cour/showadmin.html.twig', [
            'cour' => $cour,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="cour_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Cour $cour): Response
    {
        $form = $this->createForm(CourType::class, $cour);//creation de formulaire
        $form->handleRequest($request); //envoie le contenu du form

        if ($form->isSubmitted() && $form->isValid()) {
            /**
             * @var UploadedFile $file // methode nhabet fiha les fichier
             */
            $file = $form->get('imagecours')->getData();//recupere l'image
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move($this->getParameter('cours_directory'),$fileName);
            $cour->setImagecours($fileName);
            $this->getDoctrine()->getManager()->flush();


            return $this->redirectToRoute('cour_index');
        }

        return $this->render('cour/edit.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cour_delete", methods={"POST"})
     */
    public function delete(Request $request, Cour $cour): Response
    {
        if ($this->isCsrfTokenValid('delete'.$cour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cour_index');
    }
   /**
    * @Route("cour/rechreche", name="recherche_cour")
     * @throws ExceptionInterface
     */
    public function rechercheCours(Request $request, NormalizerInterface $normalizer)
    {
        $recherche = $request->get("valeurRecherche");
        $nomcours = $this->getDoctrine()->getRepository(Cour::class)->findCoursByTitre($recherche);
        $jsonContent = $normalizer->normalize($nomcours, 'json', ['groups' => 'post:read',]);
        $retour = json_encode($jsonContent);
        return new Response($retour);
    }



}
