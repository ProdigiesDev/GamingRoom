<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="fk_membre", columns={"membreid"})})
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcommande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcommande;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datecommande", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $datecommande = 'current_timestamp()';

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=0, nullable=false, options={"default"="'EnCours'"})
     */
    private $etat = '\'EnCours\'';

    /**
     * @var float
     *
     * @ORM\Column(name="totale", type="float", precision=10, scale=0, nullable=false)
     */
    private $totale = '0';

    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="membreid", referencedColumnName="id")
     * })
     */
    private $membreid;


}
