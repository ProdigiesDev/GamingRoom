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

/**
* @Route("/api/avis")
*/
class AvisApiController extends AbstractController
{
    /**
     * @Route("/", name="avis_api",methods={"GET"})
     */
    public function index(AvisRepository $avisRep,NormalizerInterface $nor): Response
    {
        $avis=$avisRep->findAll();
        $avisJson=$nor->normalize($avis,'json',['groups'=>'listAvis']);

        return new Response(json_encode($avisJson));
    }

    /**
    * @Route("/",name="add_avis",methods={"POST"})
    */
    public function add(Request $request,MembreRepository $memRep){

        $en=$this->getDoctrine()->getManager();
        $avis=new Avis();
        $avis->setAvis($request->get('avis'));
        $avis->setSentiment($request->get('sentiment'));
        $avis->setMembre($memRep->find($request->get('member_id')));

        $en->persist($avis);
        $en->flush();

        return new Response(json_encode($this->json(['code'=>200, 'message'=>'avis ajouter'],200)));
    }

    
    /**
    * @Route("/update",name="update_avis",methods={"POST"})
    */
    public function update(Request $request,AvisRepository $avisRep){

        $en=$this->getDoctrine()->getManager();
        
        $avis=$avisRep->find($request->get('id'));

        $a=$request->get('avis');
        if(strlen($a) < 5 ){
            return new Response($this->json(['code'=>400, 'message'=>'avis doit etre  > 5'],400));    
        }

        $avis->setAvis($request->get('avis'));
        $avis->setSentiment($request->get('sentiment'));

        $en->persist($avis);
        $en->flush();

        return new Response($this->json(['code'=>201, 'message'=>'avis modifier'],201));
    }

    /**
    * @Route("/delete",methods={"POST"})
    */
    public function delete(Request $request,AvisRepository $avisRep)
    {  
        $en=$this->getDoctrine()->getManager();
        
        $avis=$avisRep->find($request->get('id'));

        $en->remove($avis);
        $en->flush();
        
        return new Response($this->json(['code'=>200, 'message'=>'avis deleted'],200));
    }
}
