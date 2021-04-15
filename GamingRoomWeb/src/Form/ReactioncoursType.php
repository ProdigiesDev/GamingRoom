<?php

namespace App\Form;

use App\Entity\Reactioncours;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReactioncoursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('interaction')
            ->add('commentaire')
            ->add('dateCreation')
            ->add('cour')
            ->add('membre')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Reactioncours::class,
        ]);
    }
}
