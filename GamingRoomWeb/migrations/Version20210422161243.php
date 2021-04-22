<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210422161243 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP INDEX fk_member_avi ON avis');
        $this->addSql('ALTER TABLE avis ADD member_id  INT DEFAULT NULL, DROP member_id');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT FK_8F91ABF0B31C878D FOREIGN KEY (member_id ) REFERENCES membre (id)');
        $this->addSql('CREATE INDEX IDX_8F91ABF0B31C878D ON avis (member_id )');
        $this->addSql('ALTER TABLE categori_membre DROP FOREIGN KEY fk_catmemb_categorie');
        $this->addSql('ALTER TABLE categori_membre DROP FOREIGN KEY fk_catmemb_memebre');
        $this->addSql('ALTER TABLE categori_membre CHANGE membre_id membre_id INT DEFAULT NULL, CHANGE categorie_id categorie_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE categori_membre ADD CONSTRAINT FK_85E70079BCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie (idcat)');
        $this->addSql('ALTER TABLE categori_membre ADD CONSTRAINT FK_85E700796A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY fk_membre');
        $this->addSql('ALTER TABLE commande CHANGE membreid membreid INT DEFAULT NULL, CHANGE totale totale DOUBLE PRECISION NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DD110259B FOREIGN KEY (membreid) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE cour DROP FOREIGN KEY fk_member_cour');
        $this->addSql('ALTER TABLE cour ADD pourcentage_like DOUBLE PRECISION DEFAULT NULL, CHANGE membre_id membre_id INT DEFAULT NULL, CHANGE categorie_id categorie_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE cour ADD CONSTRAINT FK_A71F964F6A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE evenement CHANGE categorie_id categorie_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE membre CHANGE point point INT NOT NULL');
        $this->addSql('ALTER TABLE notification DROP FOREIGN KEY fk_notif_membre');
        $this->addSql('ALTER TABLE notification CHANGE `to` `to` INT DEFAULT NULL');
        $this->addSql('ALTER TABLE notification ADD CONSTRAINT FK_BF5476CAD787D2C4 FOREIGN KEY (`to`) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY fk_commande_panier');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY fk_produit_panier');
        $this->addSql('ALTER TABLE panier CHANGE produit_id produit_id INT DEFAULT NULL, CHANGE commande_id commande_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF282EA2E54 FOREIGN KEY (commande_id) REFERENCES commande (idcommande)');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF2F347EFB FOREIGN KEY (produit_id) REFERENCES produit (idprod)');
        $this->addSql('ALTER TABLE participant DROP FOREIGN KEY fk_event_participant');
        $this->addSql('ALTER TABLE participant DROP FOREIGN KEY fk_member_particper');
        $this->addSql('ALTER TABLE participant CHANGE evenement_id evenement_id INT DEFAULT NULL, CHANGE member_id member_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE participant ADD CONSTRAINT FK_D79F6B11FD02F13 FOREIGN KEY (evenement_id) REFERENCES evenement (idevent)');
        $this->addSql('ALTER TABLE participant ADD CONSTRAINT FK_D79F6B117597D3FE FOREIGN KEY (member_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE participantcours DROP FOREIGN KEY fk_cours_participant');
        $this->addSql('ALTER TABLE participantcours DROP FOREIGN KEY fk_membre_participant');
        $this->addSql('ALTER TABLE participantcours CHANGE membre_id membre_id INT DEFAULT NULL, CHANGE cour_id cour_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE participantcours ADD CONSTRAINT FK_745BEEB9B7942F03 FOREIGN KEY (cour_id) REFERENCES cour (id)');
        $this->addSql('ALTER TABLE participantcours ADD CONSTRAINT FK_745BEEB96A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE preference DROP FOREIGN KEY fk_categorie_preference');
        $this->addSql('ALTER TABLE preference DROP FOREIGN KEY fk_membre_preference');
        $this->addSql('ALTER TABLE preference CHANGE categorie_id categorie_id INT DEFAULT NULL, CHANGE member_id member_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE preference ADD CONSTRAINT FK_5D69B053BCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie (idcat)');
        $this->addSql('ALTER TABLE preference ADD CONSTRAINT FK_5D69B0537597D3FE FOREIGN KEY (member_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE produit CHANGE id_cat id_cat INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reactioncours DROP FOREIGN KEY fk_cour_reaction');
        $this->addSql('ALTER TABLE reactioncours DROP FOREIGN KEY fk_member_reaction');
        $this->addSql('ALTER TABLE reactioncours CHANGE membre_id membre_id INT DEFAULT NULL, CHANGE cour_id cour_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reactioncours ADD CONSTRAINT FK_D8A46230B7942F03 FOREIGN KEY (cour_id) REFERENCES cour (id)');
        $this->addSql('ALTER TABLE reactioncours ADD CONSTRAINT FK_D8A462306A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE reactionev DROP FOREIGN KEY fk_evenement_reaction');
        $this->addSql('ALTER TABLE reactionev DROP FOREIGN KEY fk_member_ev_reaction');
        $this->addSql('ALTER TABLE reactionev CHANGE evenement_id evenement_id INT DEFAULT NULL, CHANGE membre_id membre_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reactionev ADD CONSTRAINT FK_25AC1F1AFD02F13 FOREIGN KEY (evenement_id) REFERENCES evenement (idevent)');
        $this->addSql('ALTER TABLE reactionev ADD CONSTRAINT FK_25AC1F1A6A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
        $this->addSql('ALTER TABLE reclamation CHANGE membre_id membre_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE score DROP FOREIGN KEY fk_jeux_score');
        $this->addSql('ALTER TABLE score DROP FOREIGN KEY fk_member_score');
        $this->addSql('ALTER TABLE score CHANGE jeux_id jeux_id INT DEFAULT NULL, CHANGE membre_id membre_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE score ADD CONSTRAINT FK_32993751EC2AA9D2 FOREIGN KEY (jeux_id) REFERENCES jeux (id)');
        $this->addSql('ALTER TABLE score ADD CONSTRAINT FK_329937516A99F74A FOREIGN KEY (membre_id) REFERENCES membre (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY FK_8F91ABF0B31C878D');
        $this->addSql('DROP INDEX IDX_8F91ABF0B31C878D ON avis');
        $this->addSql('ALTER TABLE avis ADD member_id INT NOT NULL, DROP member_id ');
        $this->addSql('CREATE INDEX fk_member_avi ON avis (member_id)');
        $this->addSql('ALTER TABLE categori_membre DROP FOREIGN KEY FK_85E70079BCF5E72D');
        $this->addSql('ALTER TABLE categori_membre DROP FOREIGN KEY FK_85E700796A99F74A');
        $this->addSql('ALTER TABLE categori_membre CHANGE categorie_id categorie_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE categori_membre ADD CONSTRAINT fk_catmemb_categorie FOREIGN KEY (categorie_id) REFERENCES categorie (idcat) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE categori_membre ADD CONSTRAINT fk_catmemb_memebre FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DD110259B');
        $this->addSql('ALTER TABLE commande CHANGE membreid membreid INT NOT NULL, CHANGE totale totale DOUBLE PRECISION DEFAULT \'0\' NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT fk_membre FOREIGN KEY (membreid) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE cour DROP FOREIGN KEY FK_A71F964F6A99F74A');
        $this->addSql('ALTER TABLE cour DROP pourcentage_like, CHANGE categorie_id categorie_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE cour ADD CONSTRAINT fk_member_cour FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE evenement CHANGE categorie_id categorie_id INT NOT NULL');
        $this->addSql('ALTER TABLE membre CHANGE point point INT DEFAULT 0 NOT NULL');
        $this->addSql('ALTER TABLE notification DROP FOREIGN KEY FK_BF5476CAD787D2C4');
        $this->addSql('ALTER TABLE notification CHANGE `to` `to` INT NOT NULL');
        $this->addSql('ALTER TABLE notification ADD CONSTRAINT fk_notif_membre FOREIGN KEY (`to`) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF282EA2E54');
        $this->addSql('ALTER TABLE panier DROP FOREIGN KEY FK_24CC0DF2F347EFB');
        $this->addSql('ALTER TABLE panier CHANGE commande_id commande_id INT NOT NULL, CHANGE produit_id produit_id INT NOT NULL');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT fk_commande_panier FOREIGN KEY (commande_id) REFERENCES commande (idcommande) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT fk_produit_panier FOREIGN KEY (produit_id) REFERENCES produit (idprod) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE participant DROP FOREIGN KEY FK_D79F6B11FD02F13');
        $this->addSql('ALTER TABLE participant DROP FOREIGN KEY FK_D79F6B117597D3FE');
        $this->addSql('ALTER TABLE participant CHANGE evenement_id evenement_id INT NOT NULL, CHANGE member_id member_id INT NOT NULL');
        $this->addSql('ALTER TABLE participant ADD CONSTRAINT fk_event_participant FOREIGN KEY (evenement_id) REFERENCES evenement (idevent) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE participant ADD CONSTRAINT fk_member_particper FOREIGN KEY (member_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE participantcours DROP FOREIGN KEY FK_745BEEB9B7942F03');
        $this->addSql('ALTER TABLE participantcours DROP FOREIGN KEY FK_745BEEB96A99F74A');
        $this->addSql('ALTER TABLE participantcours CHANGE cour_id cour_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE participantcours ADD CONSTRAINT fk_cours_participant FOREIGN KEY (cour_id) REFERENCES cour (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE participantcours ADD CONSTRAINT fk_membre_participant FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE preference DROP FOREIGN KEY FK_5D69B053BCF5E72D');
        $this->addSql('ALTER TABLE preference DROP FOREIGN KEY FK_5D69B0537597D3FE');
        $this->addSql('ALTER TABLE preference CHANGE categorie_id categorie_id INT NOT NULL, CHANGE member_id member_id INT NOT NULL');
        $this->addSql('ALTER TABLE preference ADD CONSTRAINT fk_categorie_preference FOREIGN KEY (categorie_id) REFERENCES categorie (idcat) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE preference ADD CONSTRAINT fk_membre_preference FOREIGN KEY (member_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE produit CHANGE id_cat id_cat INT NOT NULL');
        $this->addSql('ALTER TABLE reactioncours DROP FOREIGN KEY FK_D8A46230B7942F03');
        $this->addSql('ALTER TABLE reactioncours DROP FOREIGN KEY FK_D8A462306A99F74A');
        $this->addSql('ALTER TABLE reactioncours CHANGE cour_id cour_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE reactioncours ADD CONSTRAINT fk_cour_reaction FOREIGN KEY (cour_id) REFERENCES cour (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reactioncours ADD CONSTRAINT fk_member_reaction FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reactionev DROP FOREIGN KEY FK_25AC1F1AFD02F13');
        $this->addSql('ALTER TABLE reactionev DROP FOREIGN KEY FK_25AC1F1A6A99F74A');
        $this->addSql('ALTER TABLE reactionev CHANGE evenement_id evenement_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE reactionev ADD CONSTRAINT fk_evenement_reaction FOREIGN KEY (evenement_id) REFERENCES evenement (idevent) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reactionev ADD CONSTRAINT fk_member_ev_reaction FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reclamation CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE score DROP FOREIGN KEY FK_32993751EC2AA9D2');
        $this->addSql('ALTER TABLE score DROP FOREIGN KEY FK_329937516A99F74A');
        $this->addSql('ALTER TABLE score CHANGE jeux_id jeux_id INT NOT NULL, CHANGE membre_id membre_id INT NOT NULL');
        $this->addSql('ALTER TABLE score ADD CONSTRAINT fk_jeux_score FOREIGN KEY (jeux_id) REFERENCES jeux (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE score ADD CONSTRAINT fk_member_score FOREIGN KEY (membre_id) REFERENCES membre (id) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
