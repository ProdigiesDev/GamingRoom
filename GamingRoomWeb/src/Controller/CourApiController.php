<?php

namespace App\Controller;

use App\Repository\CourRepository;
use App\Repository\MembreRepository;
use App\Repository\CategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Cour;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/cour/api")
 */

class CourApiController extends AbstractController
{
    /**
     * @Route("/", name="cour_api",methods={"GET"})
     */
    public function index(CourRepository $courRepository,SerializerInterface $ser): Response
    {
        $courapi=$courRepository->findAll();


         $courJson=$ser->serialize($courapi,'json',['groups'=>'listcour']);
         dump($courJson);


        return new Response(json_encode($courJson));
    }
    /**
     * @Route("/add", name="cour_api_add",methods={"POST"})
     */
    public function add(Request $request,MembreRepository $memRep,CategorieRepository $categorieRepository){

        $en=$this->getDoctrine()->getManager();
        $cour=new Cour();
        $cour->setNomcours($request->get('nomcours'));
        $cour->setDescription($request->get('description'));
        $cour->setNbParticipant($request->get('nbParticipant'));
        //$cour->setDateCreation($request->get('dateCreation'));
        $cour->setTags($request->get('tags'));
        $cour->setImagecours($request->get('imagecours'));
        $cour->setLienyoutube($request->get('lienyoutube'));
        $cour->setCategorie($request->get('categorie'));
        $cour->setCategorie($categorieRepository->find($request->get('idcat')));
        $cour->setMembre($memRep->find($request->get('member_id')));

        $en->persist($cour);
        $en->flush();

        return new Response('Cours Ajouté');
    }

    /**
     * @Route("/update",name="cour_api_update
     * ",methods={"POST"})
     */
    public function update(Request $request,CourRepository $courRepository,MembreRepository $memRep,CategorieRepository $categorieRepository){

        $en=$this->getDoctrine()->getManager();

        $cour=$courRepository->find($request->get('id'));

        $cour->setNomcours($request->get('nomcours'));
        $cour->setDescription($request->get('description'));
        $cour->setNbParticipant($request->get('nbParticipant'));
        //$cour->setDateCreation($request->get('dateCreation'));
        $cour->setTags($request->get('tags'));
        $cour->setImagecours($request->get('imagecours'));
        $cour->setLienyoutube($request->get('lienyoutube'));
        $cour->setCategorie($request->get('categorie'));
        $cour->setCategorie($categorieRepository->find($request->get('idcat')));
        $cour->setMembre($memRep->find($request->get('member_id')));

        $en->persist($cour);
        $en->flush();

        return new Response($this->json(['code'=>201, 'message'=>'Cours modifié'],201));
    }

    /**
     * @Route("/delete",methods={"POST"})
     */
    public function delete(Request $request,CourRepository $courRepository)
    {
        $en=$this->getDoctrine()->getManager();

        $cour=$courRepository->find($request->get('id'));

        $en->remove($cour);
        $en->flush();

        return new Response($this->json(['code'=>200, 'message'=>'Cours deleted'],200));
    }


    /**
     * @Route("/rechreche_api",name="rechrecheCourAPI")
     */
    public function rechreche(Request $request,CourRepository $courRepository, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Cour::class);
        //$cour=$courRepository->find($request->get('$nomcours'));
        $requestString = $request->get('searchValue');
        $offres = $repository->findOffreByNsc($requestString);
        $jsonContent = $Normalizer->normalize($offres, 'json');

        return new Response(json_encode($jsonContent));
    }
}
