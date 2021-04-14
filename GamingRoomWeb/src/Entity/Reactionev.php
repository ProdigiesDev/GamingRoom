<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reactionev
 *
 * @ORM\Table(name="reactionev", indexes={@ORM\Index(name="fk_member_ev_reaction", columns={"membre_id"}), @ORM\Index(name="fk_evenement_reaction", columns={"evenement_id"})})
 * @ORM\Entity
 */
class Reactionev
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
     * @var int
     *
     * @ORM\Column(name="interaction", type="integer", nullable=false)
     */
    private $interaction;

    /**
     * @var string|null
     *
     * @ORM\Column(name="commentaire", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $commentaire = 'NULL';

    /**
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="evenement_id", referencedColumnName="idevent")
     * })
     */
    private $evenement;

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
