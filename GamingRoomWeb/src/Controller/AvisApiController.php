<?php

namespace App\Controller;
use App\Repository\AvisRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use App\Entity\Avis;
use App\Repository\MembreRepository;
use Symfony\Contracts\HttpClient\HttpClientInterface;

/**
* @Route("/api/avis")
*/
class AvisApiController extends AbstractController
{
    private $client;

    public function __construct(HttpClientInterface $client)
    {
        $this->client = $client;
    }

    /**
     * @Route("/", name="avis_api",methods={"GET"})
     */
    public function index(AvisRepository $avisRep,NormalizerInterface $nor): Response
    {
        $avis=$avisRep->findAll();
        $avisJson=$nor->normalize($avis,'json');

        return new Response(json_encode($avisJson));
    }

    /**
     * @Route("/delete/{id}", name="avis_deletebyid",methods={"GET"})
     */
    public function deleteById(Avis $avis): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($avis);
        $entityManager->flush();
        
        return $this->json(['message'=>'avis deleted'],200);
    }

    /**
    * @Route("/addavis",name="add_avis",methods={"GET"})
    */
    public function add(Request $request,MembreRepository $memRep){

        $en=$this->getDoctrine()->getManager();
        $avis=new Avis();
        $avis->setAvis($request->query->get('avis'));
        $avis->setMembre($memRep->find($request->query->get('member_id')));

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

        $en->persist($avis);
        $en->flush();

        return new Response(json_encode($this->json(['code'=>200, 'message'=>'avis ajouter'],200)));
    }

    
}
