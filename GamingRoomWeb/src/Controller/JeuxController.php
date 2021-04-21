<?php

namespace App\Controller;

use App\Entity\Jeux;
use App\Form\JeuxType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class JeuxController extends AbstractController
{

    private $encoders ;
    private $normalizers;
    private $serializer;
    public function __construct()
    {
        $this->encoders= [ new JsonEncoder()];
        $this->normalizers= [new ObjectNormalizer()];
        $this->serializer= new Serializer($this->normalizers, $this->encoders);
    }

    /**
     * @Route("/admin/jeux", name="adminJeux")
     */
    
    public function index(): Response
    {
        $jeuxRepository = $this->getDoctrine()->getRepository(Jeux::class);
        $jeux=$jeuxRepository->findAll();
        return $this->render('jeux/index.html.twig', [
            'jeux' => $jeux,
        ]);
    }

    
    /**
     * @Route("/jeux", name="userJeux")
     */
    
    public function index2(): Response
    {
        return $this->render('jeux/list.html.twig');
    }
    
    /**
     * @Route("/api/jeux/all", name="apiAllJeux")
     */
    
    public function all()
    {
        $jeuxRepository = $this->getDoctrine()->getRepository(Jeux::class);
        $jeux=$jeuxRepository->getWebGames();
        return new Response($this->serializer->serialize($jeux,'json'));
    }
    
    /**
     * @Route("/api/jeux/{filter}", name="apiJeux")
     */
    
    public function search($filter)
    {
        $jeuxRepository = $this->getDoctrine()->getRepository(Jeux::class);
        $jeux=$jeuxRepository->search($filter);
        return new Response($this->serializer->serialize($jeux,'json'));
    }


    /**
     * @Route("/admin/gerejeux/{id}", name="adminGereJeux")
     */
    public function add(Request $request,$id): Response{
        $jeux=$this->getDoctrine()->getRepository(Jeux::class)->find($id);
        $jeux=  $jeux ? $jeux :new Jeux();
        $title=$id!=0 ? "Modifier" : "Ajouter";
        $isUpdate=$id!=0;
        $form = $this->createForm(JeuxType::class, $jeux);
        
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('fileimage')->getData();
            
            if(!$isUpdate && !$image){
                
                $this->addFlash('errors',"L'image est requise");
                return $this->render('jeux/gereJeux.html.twig', [
                    'form' => $form->createView(),
                    'title'=>$title,
                    'jeu'=> $jeux,
                    'isUpdate'=>$isUpdate
                ]);
            }
            // this condition is needed because the 'brochure' field is not required
            if ($image) {
                $originalFilename = pathinfo($image->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$image->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $image->move(
                        $this->getParameter('jeux_directory'),
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'image' property to store the PDF file name
                // instead of its contents
                $jeux->setImage($newFilename);
            }
            $em=$this->getDoctrine()->getManager();
            $em->persist($jeux);
            $em->flush();
            $this->addFlash('success','Jeux Ajouté avec success');
            return $this->redirectToRoute('adminJeux');
        }

        return $this->render('jeux/gereJeux.html.twig', [
            'form' => $form->createView(),
            'title'=>$title,
            'jeu'=> $jeux,
            'isUpdate'=>$isUpdate
        ]);
    }
    
   /**
     * @Route("/admin/jeux/supprimer/{id}", name="adminSupprimerJeux")
     */
    public function supprimer($id): Response
    {
        $filesystem = new Filesystem();
        $jeux=$this->getDoctrine()->getRepository(Jeux::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($jeux);        
        $filesystem->remove([$this->getParameter('jeux_directory').'/'.$jeux->getImage()]);
        $em->flush();
        $this->addFlash('success','Jeux supprimés avec succès');
        return $this->redirectToRoute("adminJeux");
    }
}
