<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Jeux
 *
 * @ORM\Table(name="jeux")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass=JeuxRepository::class)
 */
class Jeux
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=40, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=500, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="type_plateforme", type="string", length=0, nullable=false, options={"default"="'Desktop'"})
     */
    private $typePlateforme = '\'Desktop\'';

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=100, nullable=false)
     */
    private $image;


}
