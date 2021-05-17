<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use App\Entity\Membre;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Routing\Annotation\Route;

class MembreApiController extends AbstractController
{
    /**
     * @Route("/membre/api/login", name="membre_login")
     */
    public function signIn(Request $request): Response
    {
        $email = $request->query->get('email');
        $password = $request->query->get('password');

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Membre::class)->findOneBy(['email'=>$email]);//nlawej ala user b email
        //kan lkitah fl base
        if($user && $user->getRole() != 'Admin'){
            if($user->getRole() == 'Coach' && $user->getActive()==0 && $user->getBanDuration()==0 && $user->getLastTimeban()==null)
            {
                //return new Response("votre compte est enregistré veuillez attendre l'activation de l'admin");
                //return $this->json(['code'=>406,'message'=>'votre compte est enregistré veuillez attendre l activation de l admin'],406);
                return new Response("coach");
            }
            elseif ($user->getActive()==0 && $user->getBanDuration()>0 &&  $user->getLastTimeban()!=null)
            {
                //return new Response("votre compte est désactivé ");
                //return $this->json(['code'=>451,'message'=>'votre compte est désactivé'],451);
                return new Response("Cdesactive");
            }
            elseif(password_verify($password,$user->getPassword())) {
                $serializer = new Serializer([new ObjectNormalizer()]);
                $formatted = $serializer->normalize($user);
                return new JsonResponse($formatted);
            }
            else{
                //return new Response("password not found");
                return new Response("pass");
            }
        }
        else{
            //return new Response("email not found");
           // return  $this->json(['code'=>404,'message'=>'email not found'],404);
            return new Response("failed");
        }



    }

}
