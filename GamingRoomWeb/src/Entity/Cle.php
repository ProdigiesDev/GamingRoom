<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Cle
 *
 * @ORM\Table(name="cle", indexes={@ORM\Index(name="fk_produit_id", columns={"produit_id"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\CleRepository")
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
     * @Assert\NotBlank()
     * @ORM\Column(name="code", type="string", length=50, nullable=false)
     */
    private $code;

    /**
     * @var int

     * @Assert\NotBlank()
     * @ORM\Column(name="produit_id", type="integer", nullable=false)
     */
    private $produitId;

    public function getIdcle(): ?int
    {
        return $this->idcle;
    }

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(string $code): self
    {
        $this->code = $code;

        return $this;
    }

    public function getProduitId(): ?int
    {
        return $this->produitId;
    }

    public function setProduitId(int $produitId): self
    {
        $this->produitId = $produitId;

        return $this;
    }


}
