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
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class MembreController extends AbstractController
{



    private $encoders ;
    private $normalizers;
    private $serializer;
    /**
     * @var Security
     */
    private $security;

    public function __construct(Security $security)
    {
        $this->security = $security;
        $this->encoders= [ new JsonEncoder()];
        $this->normalizers= [new ObjectNormalizer()];
        $this->serializer= new Serializer($this->normalizers, $this->encoders);
    }


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
            $membre->setBanDuration(0);

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

    /**
     * @Route("/sendResetCode/{email}", name="send_Reset_Code", methods={"GET"})
     */
    public function sendRestCode($email,MailerInterface $mailer)
    {
        $random=random_int(1000, 9999);
        $member=$this->getDoctrine()->getRepository(Membre::class)->findByEmail($email);
        if(sizeof($member)==0){
            return new Response($this->serializer->serialize(404,'json'));
        }
        $email = (new Email())
            ->from('Gaming2020Room@gmail.com')
            ->to($email)
            ->subject('Reset password code')
            ->html('<p>Hello,  Here is your code :'.$random.' </p>');


        $mailer->send($email);
        return new Response($this->serializer->serialize($random,'json'));
    }

    /**
     * @Route("/resetPass/{email}/{password}", name="send_Reset_Pass", methods={"GET"})
     */
    public function resetPass($email,$password,UserPasswordEncoderInterface $encoder)
    {
        $membre=$this->getDoctrine()->getRepository(Membre::class)->findByEmail($email);

        if(sizeof($membre)==0){
            return new Response($this->serializer->serialize(404,'json'));
        }

        $membre=$membre[0];
        $membre->setPassword($encoder->encodePassword($membre, $password));
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($membre);
        $entityManager->flush();



        return new Response($this->serializer->serialize(200,'json'));
    }

    /**
     * @Route("/member/{id}/activer", name="membre_activer", methods={"GET","POST"})
     */
    public function activerCompte( Membre $membre,$id)
    {
        $membre=$this->getDoctrine()->getRepository(Membre::class)->find($id);


        $membre->setActive(1);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($membre);
        $entityManager->flush();



        return $this->redirectToRoute('membre_index');
    }
    /**
     * @Route("/member/{id}/desactiver", name="membre_desactiver", methods={"GET","POST"})
     */
    public function desactiverCompte(Request $request, Membre $membre,$id)
    {
        $membre=$this->getDoctrine()->getRepository(Membre::class)->find($id);


        $membre->setActive(0);
        $membre->setBanDuration($membre->getBanDuration()+1);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($membre);
        $entityManager->flush();



        return $this->redirectToRoute('membre_index');
    }
    /**
     * @Route("/rechreche",name="rechrecheMembre")
     */
    public function rechreche(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Membre::class);
        $requestString = $request->get('searchValue');
        $membres = $repository->findByEmailAndRole($requestString);
        $jsonContent = $Normalizer->normalize($membres, 'json');

        return new Response(json_encode($jsonContent));
    }

}