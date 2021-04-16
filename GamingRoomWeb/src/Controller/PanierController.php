<?php

namespace App\Controller;

use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class PanierController extends AbstractController
{

    /**
     * @Route("/panier", name="panier")
     * @param SessionInterface $session
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function index(SessionInterface $session, ProduitRepository $produitRepository): Response
    {
        $panier = $session->get('panier',[]);

        //dd($panier);
        return $this->render('panier/index.html.twig', [
            'items' => $panier
        ]);
    }

    /**
     * @Route("/panier/updateProdPanier/{id}/{nb}", name="updateProdPanier")
     * @param $id
     * @param SessionInterface $session
     */

    public function updateProdPanier($id, $nb,SessionInterface $session, ProduitRepository $produitRepository) {

        $panier = $session->get('panier',[]);
        $produit=$produitRepository->find($id);
        if(!$produit){
            return $this->redirectToRoute('panier');
        }
       // dd($panier[$id]['produit']->getIdprod());
        if (!empty($panier[$id])) {
            $newqt=$panier[$id]['quantity']+intval($nb);
            if($newqt<0)
                $newqt=0;

            $panier[$id]= [
                'produit' => $produitRepository->find($id),
                'quantity'=> $newqt
            ];

        }else {
            $panier[$id] =[
                'produit' => $produitRepository->find($id),
                'quantity'=> 1
            ];
        }
        $session -> set('panier',$panier);

        return $this->redirectToRoute('panier');

    }

    /**
     * @Route("/clearpanier", name="emptypanier")
     * @param SessionInterface $session
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function clear(SessionInterface $session): Response
    {
        $panier = $session->set('panier',[]);


        return $this->redirectToRoute('panier');
    }

}
