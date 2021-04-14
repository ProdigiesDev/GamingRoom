<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Contracts\HttpClient\HttpClientInterface;

use App\Entity\Membre;
use App\Entity\Avis;
use App\Form\AvisType;
class HomeController extends AbstractController
{
    private $client;
    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }
    /**
     * @Route("/", name="home")
     */
    public function index(Request $request): Response
    {
        $avis= new Avis();
        
        $form = $this->createForm(AvisType::class, $avis);
        
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $id=6;
            $avis->setMembre($this->getDoctrine()->getRepository(Membre::class)->find($id));
            $response = $this->client->request(
                'GET',
                "https://api.meaningcloud.com/sentiment-2.1?verbose=y&key=".$_ENV['keyMeaningcloudApi']."&lang=en&txt=".$avis->getAvis()."&model=general"
            );
    
            $statusCode = $response->getStatusCode();
            
            if($statusCode==200){
                // $statusCode = 200
                $content = $response->toArray();
                $avis->setSentiment($content['score_tag']);
            }
            
            $em=$this->getDoctrine()->getManager();
            $em->persist($avis);
            $em->flush();
            //TODO notif add succ
            $avis= new Avis();
            $form = $this->createForm(AvisType::class, $avis);
        }

        return $this->render('home/index.html.twig', [
            'form' => $form->createView(),
        ]);
    }
}
