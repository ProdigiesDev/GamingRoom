<?php

namespace App\Controller;

use App\Entity\Categorie;
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
    public function index(CourRepository $courRepository,NormalizerInterface $nor): Response
    {
        $courapi=$courRepository->findAll();


         $courJson=$nor->normalize($courapi,'json');
         dump($courJson);


        return new Response(json_encode($courJson));
    }

    /**
     * @Route("/add/", name="cour_api_add",methods={"GET"})
     */
    public function add(Request $request,MembreRepository $memRep,CategorieRepository $categorieRepository){

        $en=$this->getDoctrine()->getManager();
        $cour=new Cour();
        $cour->setNomcours((String)$request->query->get('nomcours'));
        $cour->setDescription((String)$request->query->get('description'));
        $cour->setNbParticipant((int)$request->query->get('nbParticipant'));
        $cour->setDateCreation(new \DateTime());
        $cour->setTags((String)$request->query->get('tags'));
        $cour->setImagecours((String)$request->query->get('imagecours'));
        $cour->setLienyoutube((String)$request->query->get('lienyoutube'));
        $cour->setCategorie($request->query->get('categorie'));
        $cour->setCategorie($categorieRepository->find((int)$request->query->get('idcat')));
        $cour->setMembre($memRep->find((int)$request->query->get('member_id')));

        $en->persist($cour);
        $en->flush();

        return new Response('Cours Ajouté');
    }

    /**
     * @Route("/update
     * ",name="cour_api_update
     * ",methods={"POST"})
     */
    public function update(Request $request,CourRepository $courRepository,MembreRepository $memRep,CategorieRepository $categorieRepository){

        $en=$this->getDoctrine()->getManager();
        $cour=new Cour();
        $cour->setNomcours((String)$request->query->get('nomcours'));
        $cour->setDescription((String)$request->query->get('description'));
        $cour->setNbParticipant((int)$request->query->get('nbParticipant'));
        $cour->setDateCreation(new \DateTime());
        $cour->setTags((String)$request->query->get('tags'));
        $cour->setImagecours((String)$request->query->get('imagecours'));
        $cour->setLienyoutube((String)$request->query->get('lienyoutube'));
        $cour->setCategorie($request->query->get('categorie'));
        $cour->setCategorie($categorieRepository->find((int)$request->query->get('idcat')));
        $cour->setMembre($memRep->find((int)$request->query->get('member_id')));

        $en->persist($cour);
        $en->flush();

        return new Response('Cours Modifié');
    }

    /**
     * @Route("/delete/{id}", name="delete_cour")
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


    /**
     * @Route("/{id}", name="cour_api_show", methods={"GET"})
     */
    public function show(Cour $cour, NormalizerInterface $nor): Response
    {
        $courJson=$nor->normalize($cour,'json');
        return new Response(json_encode($courJson));
    }
}
