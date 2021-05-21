<?php

namespace App\Controller;

use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Commande;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Membre;
use App\Entity\Panier;
use App\Entity\Produit;
use Twilio\Rest\Client;

use Symfony\Component\Security\Core\Security;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;

use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Json;
class PanierController extends AbstractController
{
    private $twilio;
    private $security;

    public function  __construct(Security $security,Client $twilio) {
        $this->security = $security;
        $this->twilio = $twilio;
    }

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
        $user = $this->security->getUser();
        if(!$user){
            return $this->redirect('/login');
        }
        $panier = $session->get('panier',[]);
        $total= 0;

        foreach($panier as $item){
            $totalitem = $item ['produit'] -> getPrix() * $item['quantity'];
            $total += $totalitem;
        }
        
        if(  $request->query->has('billing_firstname')){

            $name=$request->query->get('billing_firstname');
            $last=$request->query->get('billing_lastname');
            $sender = $this->getParameter('twilio_number');
                $message = $this->twilio->messages->create(
                    '+21629181857', // Send text to this number
                    array(
                    'from' => $sender, // My Twilio phone number
                    'body' => 'Bonjour  '. $name.', votre commande est en cours de traitment.
                    GamingRoom ' 
                    )
                );


            $commande=new Commande();
            $commande->setMembreid($user);
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

            return $this->redirectToRoute('successpanier');
            
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
     * @Route("/payment", name="payment")
     * @param SessionInterface $session
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function payment(Request $request,SessionInterface $session, ProduitRepository $produitRepository): Response
    {
        //$panier = $session->get('panier',[]);

        $panier = $session->get('panier',[]);
        $total= 0;

        foreach($panier as $item){
            $totalitem = $item ['produit'] -> getPrix() * $item['quantity'];
            $total += $totalitem;
        }
        return $this-> render('panier/payment.html.twig' , [
            'items' => $panier,
            'total'=> $total
        ]);

    }

    /**
     * @Route("/panier/updateProdPanier/{id}/{nb}", name="updateProdPanier")
     * @param $id
     * @param SessionInterface $session
     */

    public function updateProdPanier($id, $nb,Request $req,SessionInterface $session, ProduitRepository $produitRepository) {

       
        
        $panier = $session->get('panier',[]);
        $produit=$produitRepository->find($id);
        if(!$produit){
            return $this->redirectToRoute('panier');
        }
       // dd($panier[$id]['produit']->getIdprod());
       if($req->get('qt')){
        $nb=intval($req->get('qt'));
        if($nb<0)
            $nb=0;
        if (!empty($panier[$id])) {

            $panier[$id]= [
                'produit' => $produitRepository->find($id),
                'quantity'=> $nb
            ];

        }else {
            $panier[$id] =[
                'produit' => $produitRepository->find($id),
                'quantity'=>$nb
            ];
        }
        $session -> set('panier',$panier);
        return $this->redirectToRoute('panier');
       }
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

        return $this->redirectToRoute('front');

    }

    /**
     * @Route("/success-payment", name="successpanier")
     * @param SessionInterface $session
     * @param ProduitRepository $produitRepository
     * @return Response
     */
    public function success(SessionInterface $session): Response
    {
        
        $panier = $session->get('panier',[]);
        $total= 0;

        foreach($panier as $item){
            $totalitem = $item ['produit'] -> getPrix() * $item['quantity'];
            $total += $totalitem;
        }
        
        return $this-> render('panier/success.html.twig' , [
            'items' => $panier,
            'total'=> $total
        ]);
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

    /***********************************************************************************/
     /**
      * @Route("/addprod", name="addprod")
    
      */

    public function ajouterProd(Request $request)
    {
        $panier = new Panier();
        $produit = $request->query->get("prodid");
        $quantitydemande = $request->query->get("quant");
        $em = $this->getDoctrine()->getManager();
        

        $panier->setProduit($produit);
        $panier->setQuantityDemande($quantitydemande);
        

        $em->persist($panier);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse($formatted);

    }
     /**
      * @Route("/paniermob", name="paniermob")
      */
      public function affichePanMob()
      {
 
          $panier = $this->getDoctrine()->getManager()->getRepository(Panier::class)->findAll();
          $serializer = new Serializer([new ObjectNormalizer()]);
          $formatted = $serializer->normalize($panier);
 
          return new JsonResponse($formatted);
 
      }

    
     /**
      * @Route("/clearmob", name="emptymob")
      */

      public function deletePanMob(Request $request) {
        
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $panier = $em->getRepository(Panier::class)->find($id);
        if($panier!=null ) {
            $em->remove($panier);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Panier a ete vider avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id panier invalide.");
    }
     /**
     * @Route("/updatepanmob", name="updatepan")
     */
    public function modifierQt(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $panier = $this->getDoctrine()->getManager()
                        ->getRepository(panier::class)
                        ->find($request->get("id"));

        $panier->setQuantityDemande($request->get("quantite"));

        $em->persist($panier);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($panier);
        return new JsonResponse("panier a ete modifiee avec success.");

    }
}