<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cle
 *
 * @ORM\Table(name="cle", indexes={@ORM\Index(name="fk_produit_id", columns={"produit_id"})})
 * @ORM\Entity
 */
class Cle
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcle", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcle;

    /**
     * @var string
     *
     * @ORM\Column(name="code", type="string", length=50, nullable=false)
     */
    private $code;

    /**
     * @var int
     *
     * @ORM\Column(name="produit_id", type="integer", nullable=false)
     */
    private $produitId;


}
