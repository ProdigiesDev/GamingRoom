<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Membre
 *
 * @ORM\Table(name="membre", uniqueConstraints={@ORM\UniqueConstraint(name="email", columns={"email"})})
 * @ORM\Entity
 */
class Membre
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
     * @ORM\Column(name="nom", type="string", length=20, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=20, nullable=false)
     */
    private $prenom;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_naissance", type="date", nullable=false)
     */
    private $dateNaissance;

    /**
     * @var string
     *
     * @ORM\Column(name="genre", type="string", length=0, nullable=false)
     */
    private $genre;

    /**
     * @var string
     *
     * @ORM\Column(name="numero_tel", type="string", length=8, nullable=false)
     */
    private $numeroTel;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=255, nullable=false)
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=50, nullable=false)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="role", type="string", length=0, nullable=false)
     */
    private $role;

    /**
     * @var int
     *
     * @ORM\Column(name="point", type="integer", nullable=false)
     */
    private $point = '0';

    /**
     * @var string|null
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=true, options={"default"="NULL"})
     */
    private $description = 'NULL';

    /**
     * @var bool
     *
     * @ORM\Column(name="active", type="boolean", nullable=false)
     */
    private $active;

    /**
     * @var int|null
     *
     * @ORM\Column(name="ban_duration", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $banDuration = NULL;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="last_timeban", type="datetime", nullable=true, options={"default"="NULL"})
     */
    private $lastTimeban = 'NULL';


}
