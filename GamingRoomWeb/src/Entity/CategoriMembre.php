<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * CategoriMembre
 *
 * @ORM\Table(name="categori_membre", indexes={@ORM\Index(name="fk_catmemb_memebre", columns={"membre_id"}), @ORM\Index(name="fk_catmemb_categorie", columns={"categorie_id"})})
 * @ORM\Entity
 */
class CategoriMembre
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
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="idcat")
     * })
     */
    private $categorie;

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
