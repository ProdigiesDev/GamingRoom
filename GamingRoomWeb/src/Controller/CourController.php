<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Entity\Reactioncours;
use App\Form\CourType;
use App\Repository\CourRepository;
use Knp\Component\Pager\PaginatorInterface;
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
    public function index(CourRepository $courRepository, PaginatorInterface $paginator, Request $request): Response
    {
        $courRepository = $paginator->paginate($this->getDoctrine()->getRepository(Cour::class)
            ->findAll(),
            $request->query->getInt('page', 1),
            5
        );
        return $this->render('cour/index.html.twig', [
            'cours' => $courRepository,
        ]);

        $writer = $this->get('phpspreadsheet')->createSpreadSheet();
        $writer->setActiveSheetIndex(0);
        $activesheet = $writer->getActiveSheet();
        $drawingobject = $this->get('phpspreadsheet')->createSpreadsheetWorksheetDrawing();
        $drawingobject->setPath('/path/to/image')
            ->setName('Image name')
            ->setDescription('Image description')
            ->setHeight(60)
            ->setOffsetY(20)
            ->setCoordinates('A1')
            ->setWorksheet($activesheet);

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
            $fileName = bin2hex(random_bytes(6)) . '.' . $file->guessExtension();
            $file->move($this->getParameter('cours_directory'), $fileName);
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
            $fileName = bin2hex(random_bytes(6)) . '.' . $file->guessExtension();
            $file->move($this->getParameter('cours_directory'), $fileName);
            $cour->setImagecours($fileName);
            $cour->setImagecours($fileName);
            //entity managerpermet l’insertion, la mise à jour et la suppression des données de la base de données
            $entityManager = $this->getDoctrine()->getManager();//récupérer l’entity manager
            $entityManager->persist($cour);//pour l‘ajout d’un nouvel objet
            $entityManager->flush();//envoyer la maj à la bd
            $this->addFlash(
                'info', 'Added succesfully'
            );

            return $this->redirectToRoute('cour_index_admin'); //redirection apres l'ajout
        }


        return $this->render('cour/newadmin.html.twig', [ //envoi du form à la page twig
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/rechreche",name="rechrecheCour")
     */
    public function rechreche(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Cour::class);
        $requestString = $request->get('searchValue');
        $offres = $repository->findOffreByNsc($requestString);
        $jsonContent = $Normalizer->normalize($offres, 'json');

        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/{id}", name="cour_show", methods={"GET"})
     */
    public function show(Cour $cour): Response
    {
        return $this->render('cour/show.html.twig', [
            'reactions' => $this->getDoctrine()->getRepository(Reactioncours::class)->findAll(),
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
            $fileName = bin2hex(random_bytes(6)) . '.' . $file->guessExtension();
            $file->move($this->getParameter('cours_directory'), $fileName);
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
     * @Route("/{id}/editadmin", name="cour_edit_admin", methods={"GET","POST"})
     */
    public function editadmin(Request $request, Cour $cour): Response
    {
        $form = $this->createForm(CourType::class, $cour);//creation de formulaire
        $form->handleRequest($request); //envoie le contenu du form

        if ($form->isSubmitted() && $form->isValid()) {
            /**
             * @var UploadedFile $file // methode nhabet fiha les fichier
             */
            $file = $form->get('imagecours')->getData();//recupere l'image
            $fileName = bin2hex(random_bytes(6)) . '.' . $file->guessExtension();
            $file->move($this->getParameter('cours_directory'), $fileName);
            $cour->setImagecours($fileName);
            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info', 'uptated succesfully'
            );


            return $this->redirectToRoute('cour_index_admin');
        }

        return $this->render('cour/editadmin.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cour_delete", methods={"POST"})
     */
    public function delete(Request $request, Cour $cour): Response
    {
        $reactions = $this->getDoctrine()->getRepository(Reactioncours::class)->findBy(["cour" => $cour]);

        foreach ($reactions as $reaction){
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reaction);
            $entityManager->flush();
        }
        if ($this->isCsrfTokenValid('delete' . $cour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cour_index');
    }

    /**
     * @Route("deleteadmin/{id}", name="cour_delete_admin", methods={"POST"})
     */
    public function deleteadmin(Request $request, Cour $cour): Response
    {
        if ($this->isCsrfTokenValid('delete' . $cour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cour_index_admin');
    }


}
