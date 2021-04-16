<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Evenement
 *
 * @ORM\Table(name="evenement", indexes={@ORM\Index(name="fk_idcat", columns={"categorie_id"})})
 * @ORM\Entity
 */
class Evenement
{
    /**
     * @var int
     *
     * @ORM\Column(name="idevent", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idevent;

    /**
     * @var string
     *
     * @ORM\Column(name="nomevent", type="string", length=30, nullable=false)
     */
    private $nomevent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datedeb", type="date", nullable=false)
     */
    private $datedeb;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datefin", type="date", nullable=false)
     */
    private $datefin;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    /**
     * @var int
     *
     * @ORM\Column(name="nbremax_participant", type="integer", nullable=false)
     */
    private $nbremaxParticipant;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="lieu", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $lieu = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="lienyoutube", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $lienyoutube = 'NULL';

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="idcat")
     * })
     */
    private $categorie;


}
