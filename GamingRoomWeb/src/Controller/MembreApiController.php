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
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

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
        $user = $em->getRepository(Membre::class)->findOneBy(['email'=>$email]);
        //kan lkitah fl base
        if($user){

            if(password_verify($password,$user->getPassword())) {
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

    /**
     * @Route("/membre/api/fpass", name="membre_fpass")
     */

    public function forgotPassword(Request $request,UserPasswordEncoderInterface $encoder): Response
    {
        $email = $request->get('email');
        $password = $request->query->get('password');

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Membre::class)->findOneBy(['email' => $email]);

        if ($user) {
            $user->setPassword($encoder->encodePassword($user, $password));
            try {
                $em->persist($user);
                $em->flush();
                return new JsonResponse("success", 200);
            } catch (\Exception $ex) {
                return new Response("fail" . $ex->getMessage());
            }

        } else {
            return new Response("ErrorEmail");
        }
    }
    /////////////////////////debut resest password ////////////////////

    /**
     * @Route("/membre/api/verifEmail", name="membre_vemail")
     */

    public function VerifierEmail(Request $request,UserPasswordEncoderInterface $encoder,MailerInterface $mailer): Response
    {
        $email = $request->query->get('email');
        $em = $this->getDoctrine()->getManager();
        $random=random_int(1000, 9999);
        $member=$this->getDoctrine()->getRepository(Membre::class)->findByEmail($email);
        if(sizeof($member)==0){
            return new Response("404");
        }
        $email = (new Email())
            ->from('Gaming2020Room@gmail.com')
            ->to($email)
            ->subject('Reset password code')
            ->html('<p>Hello,  Here is your code :'.$random.' </p>');


        $mailer->send($email);
        return new Response($random);
    }
    /////////////////////////fin reset password ////////////////////////
    /**
     * @Route("/membre/api/signup", name="user_signup")
     */
    public function signUp(Request $request,UserPasswordEncoderInterface $encoder ): Response
    {
        $nom = $request->query->get('nom');
        $prenom = $request->query->get('prenom');
        $date = $request->query->get('dateNaissance');
        $genre = $request->query->get('genre');
        $numero = $request->query->get('numeroTel');
        $email = $request->query->get('email');
        $pass = $request->query->get('password');
        $image = $request->query->get('image');
        $role = $request->query->get('role');
        $desc = $request->query->get('description');

        $em = $this->getDoctrine()->getManager();
        $membre = $em->getRepository(Membre::class)->findOneBy(['email'=>$email]);


        //test addresse lazm bl @
        if(!filter_var($email,FILTER_VALIDATE_EMAIL)){
            return new Response("email invalide");
        }
        if($membre){
            return new Response("email existe");
        }
        $user = new Membre();
        $user->setNom($nom);
        $user->setPrenom($prenom);
        $user->setDateNaissance(new \DateTime($date));
        $user->setGenre($genre);
        $user->setNumeroTel($numero);
        $user->setEmail($email);
        $user->setPassword($encoder->encodePassword($user, $pass));
        $user->setImage($image);
        $user->setRole($role);
        $user->setDescription($desc);
        $user->setLastTimeban(null);
        $user->setBanDuration(0);

        if($user->getRole()=="Coach"){
            $user->setActive(0);
        }
        if($user->getRole()=="Membre") {
            $user->setActive(1);
        }

        try{

            $em->persist($user);
            $em->flush();

            return new JsonResponse("compte cree",200);
        }catch (\Exception $ex){
            return new Response("exception".$ex->getMessage());
        }
    }



}
