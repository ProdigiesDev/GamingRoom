<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cour
 *
 * @ORM\Table(name="cour", indexes={@ORM\Index(name="fk_member_cour", columns={"membre_id"}), @ORM\Index(name="fk_categorie_cour", columns={"categorie_id"})})
 * @ORM\Entity
 */
class Cour
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
     * @ORM\Column(name="nomCours", type="string", length=100, nullable=false)
     */
    private $nomcours;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_participant", type="integer", nullable=false)
     */
    private $nbParticipant;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_creation", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $dateCreation = 'current_timestamp()';

    /**
     * @var string
     *
     * @ORM\Column(name="tags", type="string", length=255, nullable=false)
     */
    private $tags;

    /**
     * @var string
     *
     * @ORM\Column(name="imagecours", type="string", length=250, nullable=false)
     */
    private $imagecours;

    /**
     * @var string
     *
     * @ORM\Column(name="lienYoutube", type="string", length=255, nullable=false)
     */
    private $lienyoutube;

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="idcat")
     * })
     */
    private $categorie;

    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="membre_id", referencedColumnName="id")
     * })
     */
    private $membre;


}
