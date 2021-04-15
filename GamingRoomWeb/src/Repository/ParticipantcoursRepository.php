<?php

namespace App\Repository;

use App\Entity\Participantcours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Participantcours|null find($id, $lockMode = null, $lockVersion = null)
 * @method Participantcours|null findOneBy(array $criteria, array $orderBy = null)
 * @method Participantcours[]    findAll()
 * @method Participantcours[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ParticipantcoursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Participantcours::class);
    }

    // /**
    //  * @return Participantcours[] Returns an array of Participantcours objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Participantcours
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
