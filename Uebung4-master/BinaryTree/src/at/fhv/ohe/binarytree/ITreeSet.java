package at.fhv.ohe.binarytree;

import java.util.List;

public interface ITreeSet<T> {

    void add(T element);

    boolean contains(T element);

    List<T> toArray(ReturnOrder order);

    int leaveCount();

    int height();

    int size();

    enum ReturnOrder {
        PreOrder,
        InOrder,
        PostOrder,
        LevelByLevel
    }
}
