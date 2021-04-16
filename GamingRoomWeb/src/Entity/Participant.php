<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participant
 *
 * @ORM\Table(name="participant", indexes={@ORM\Index(name="fk_member_particper", columns={"member_id"}), @ORM\Index(name="fk_event_participant", columns={"evenement_id"})})
 * @ORM\Entity
 */
class Participant
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
     * @var string|null
     *
     * @ORM\Column(name="duel", type="string", length=10, nullable=true, options={"default"="NULL"})
     */
    private $duel = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="round", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $round = NULL;

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
     *   @ORM\JoinColumn(name="member_id", referencedColumnName="id")
     * })
     */
    private $member;


}
