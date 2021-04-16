<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participantcours
 *
 * @ORM\Table(name="participantcours", indexes={@ORM\Index(name="fk_cours_participant", columns={"cour_id"}), @ORM\Index(name="fk_membre_participant", columns={"membre_id"})})
 * @ORM\Entity
 */
class Participantcours
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
     * @var \Cour
     *
     * @ORM\ManyToOne(targetEntity="Cour")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="cour_id", referencedColumnName="id")
     * })
     */
    private $cour;

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
