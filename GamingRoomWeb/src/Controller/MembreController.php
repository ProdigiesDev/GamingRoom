<?php

namespace App\Controller;

use App\Entity\Membre;
use App\Form\MembreType;
use App\Repository\MembreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Core\Security;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;

class MembreController extends AbstractController
{
    /**
     * @Route("/admin/member", name="membre_index", methods={"GET"})
     */
    public function index(MembreRepository $membreRepository,Request $request,PaginatorInterface $paginator): Response
    {
        $membre = $paginator->paginate(
        // Doctrine Query, not results
            $membreRepository->findAll(),
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            5
        );
        return $this->render('membre/index.html.twig', [
            'membres' => $membre,
        ]);
    }

    /**
     * @Route("membre/new", name="membre_new", methods={"GET","POST"})
     */
    public function ajouterMembre(Request $request,UserPasswordEncoderInterface $encoder): Response
    {
        $membre = new Membre();
        $form = $this->createForm(MembreType::class, $membre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $membre->setLastTimeban(  $membre->getDateNaissance());

            if($membre->getRole()=="Coach"){
                $membre->setActive(0);
            }
            if($membre->getRole()=="Membre"){
                $membre->setActive(1);
            }
            $membre->setPassword($encoder->encodePassword($membre, $membre->getPassword()));
            $file = $form->get('image')->getData();
            $fileName = bin2hex(random_bytes(6)).'.'.$file->guessExtension();
            $file->move ($this->getParameter('membre_directory'),$fileName);
            $membre->setImage($fileName);


            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($membre);
            $entityManager->flush();

            return $this->redirectToRoute('home');
        }

        return $this->render('membre/new.html.twig', [
            'membre' => $membre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/member/{id}", name="membre_show", methods={"GET"})
     */
    public function afficherMembre(Membre $membre): Response
    {
        return $this->render('membre/show.html.twig', [
            'membre' => $membre,
        ]);
    }

    /**
     * @Route("/member/{id}/edit", name="membre_edit", methods={"GET","POST"})
     */
    public function modifierMembre(Request $request, Membre $membre): Response
    {
        $form = $this->createForm(MembreType::class, $membre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('membre_index');
        }

        return $this->render('membre/edit.html.twig', [
            'membre' => $membre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/member/{id}", name="membre_delete", methods={"POST"})
     */
    public function supprimerMembre(Request $request, Membre $membre): Response
    {
        if ($this->isCsrfTokenValid('delete'.$membre->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($membre);
            $entityManager->flush();
        }

        return $this->redirectToRoute('membre_index');
    }

    /**
     * @var Security
     */
    private $security;

    public function __construct(Security $security)
    {
        $this->security = $security;
    }
    /**
     * @Route("/profil/{id}", name="profil", methods={"GET"})
     */
    public function AfficherProfil() : Response
    {
        $user = $this->security->getUser(); // null or UserInterface, if logged in
        // ... do whatever you want with $user
        if(!$user){
            return $this->redirectToRoute('home');
        }
        return $this->render('membre/profil.html.twig', [
            'user' => $user,
        ]);
    }
    /**
     * @Route("/admin/member/pdf", name="membre_pdf", methods={"GET"})
     */
    public function membrePdf(MembreRepository $membreRepository): Response
    {

       $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        $dompdf = new Dompdf($pdfOptions);

            $membres = $membreRepository->findAll();


        $html = $this->renderView('membre/pdf.html.twig', [
            'membres' => $membres,
        ]);

        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'landscape');
        $dompdf->render();
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
       /* return $this->render('membre/pdf.html.twig',['membres'=> $membreRepository->findAll()]);*/

    }
}
