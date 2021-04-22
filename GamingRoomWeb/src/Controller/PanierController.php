<?php

namespace App\Controller;

use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Commande;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Membre;
use App\Entity\Panier;
use App\Entity\Produit;
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
        $total= 0;

        foreach($panier as $item){
            $totalitem = $item ['produit'] -> getPrix() * $item['quantity'];
            $total += $totalitem;
        }
        return $this-> render('panier/index.html.twig' , [
            'items' => $panier,
            'total'=> $total
        ]);

    }


     /**
     * @Route("/checkout", name="checkout")
     * @param SessionInterface $session
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function checkout(Request $request,SessionInterface $session, ProduitRepository $produitRepository): Response
    {
        $panier = $session->get('panier',[]);
        $total= 0;

        foreach($panier as $item){
            $totalitem = $item ['produit'] -> getPrix() * $item['quantity'];
            $total += $totalitem;
        }
        
        if(  $request->query->has('billing_firstname')){

            $name=$request->query->get('billing_firstname');
            $last=$request->query->get('billing_lastname');


            $mem=$this->getDoctrine()->getRepository(Membre::class)->find(6);
            $commande=new Commande();
            $commande->setMembreid($mem);
            $commande->setTotale($total);
            $commande->setEtat("EnAttente");
            $em=$this->getDoctrine()->getManager();
            $em->persist($commande);
            $em->flush();

            foreach($panier as $item){
                $panierS=new  Panier();
                $panierS->setCommande($commande);
                $panierS->setQuantitydemande($item['quantity']);
                $panierS->setProduit($this->getDoctrine()->getRepository(Produit::class)->find($item ['produit']->getIdprod()));
                $em->persist($panierS);
                $em->flush();
            }
            
            $panier = $session->set('panier',[]);

            return $this->redirectToRoute('home');
            
        }
        if($total==0){
             return $this->redirectToRoute('panier');
        }

        return $this-> render('panier/checkout.html.twig' , [
            'items' => $panier,
            'total'=> $total
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
            $newqt=intval($nb);
            if($newqt<0)
                $newqt=0;
            $panier[$id] =[
                'produit' => $produitRepository->find($id),
                'quantity'=>$newqt
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

    /**
     * @Route("/panier/supprimerProd/{id}", name="panier_supprime")
     */

    public function supprimerProd($id, SessionInterface $session) {
        $panier = $session->get('panier', []);

        if (!empty($panier[$id])) {
            unset($panier[$id]);
        }

        $session->set('panier',$panier);

        return $this->redirectToRoute("panier");

    }

}
