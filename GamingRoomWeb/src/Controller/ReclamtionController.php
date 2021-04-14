<?php

namespace App\Controller;

use App\Entity\Reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReclamtionController extends AbstractController
{
    /**
     * @Route("admin/reclamtion", name="adminReclamtion")
     */
    public function index(): Response
    {  
        $recRepository = $this->getDoctrine()->getRepository(Reclamation::class);
        $rec=$recRepository->findAll();
        return $this->render('reclamtion/index.html.twig', [
            'rec' => $rec,
        ]);
    }

    
   /**
     * @Route("/admin/reclamtion/supprimer/{id}", name="adminSupprimerReclamtion")
     */
    public function supprimer($id): Response
    {
        $rec=$this->getDoctrine()->getRepository(Reclamation::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($rec);        
        $em->flush();
        $this->addFlash('success','Reclamtion supprimés avec succès');
        return $this->redirectToRoute("adminReclamtion");
    }
}
