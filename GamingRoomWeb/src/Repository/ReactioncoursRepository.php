<?php

namespace App\Repository;

use App\Entity\Reactioncours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Reactioncours|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reactioncours|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reactioncours[]    findAll()
 * @method Reactioncours[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReactioncoursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Reactioncours::class);
    }

    // /**
    //  * @return Reactioncours[] Returns an array of Reactioncours objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Reactioncours
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
