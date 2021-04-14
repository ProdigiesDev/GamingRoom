<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reactioncours
 *
 * @ORM\Table(name="reactioncours", indexes={@ORM\Index(name="fk_member_reaction", columns={"membre_id"}), @ORM\Index(name="fk_cour_reaction", columns={"cour_id"})})
 * @ORM\Entity
 */
class Reactioncours
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
     * @var \DateTime
     *
     * @ORM\Column(name="date_creation", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $dateCreation = 'current_timestamp()';

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
