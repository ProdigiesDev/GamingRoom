<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * Panier
 *
 * @ORM\Table(name="panier", indexes={@ORM\Index(name="fk_produit_panier", columns={"produit_id"}), @ORM\Index(name="fk_commande_panier", columns={"commande_id"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\PanierRepository")
 */
class Panier
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
     * @ORM\Column(name="quantityDemande", type="integer", nullable=false)
     * @Groups({"post:read"})
     */
    private $quantitydemande;

    /**
     * @var \Commande
     *
     * @ORM\ManyToOne(targetEntity="Commande")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="commande_id", referencedColumnName="idcommande")
     * })
      * @Groups({"post:read"})
     */
    private $commande;

    /**
     * @var \Produit
     *
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="produit_id", referencedColumnName="idprod")
     * })
          * @Groups({"post:read"})

     */
    private $produit;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getQuantitydemande(): ?int
    {
        return $this->quantitydemande;
    }

    public function setQuantitydemande(int $quantitydemande): self
    {
        $this->quantitydemande = $quantitydemande;

        return $this;
    }

    public function getCommande(): ?Commande
    {
        return $this->commande;
    }

    public function setCommande(?Commande $commande): self
    {
        $this->commande = $commande;

        return $this;
    }

    public function getProduit(): ?Produit
    {
        return $this->produit;
    }

    public function setProduit(?Produit $produit): self
    {
        $this->produit = $produit;

        return $this;
    }


}
