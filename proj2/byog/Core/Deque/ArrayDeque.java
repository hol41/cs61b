package byog.Core.Deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] repo;
    private int startIndex;
    private int endIndex;

    public ArrayDeque() {
        repo = (T []) new Object[8];
        endIndex = 0;
        startIndex = 0;
    }


    private int loopPos(int x, int len) {
        if (x < 0) {
            if ((0 - x) % len == 0) {
                return 0;
            }
            return len - (-x) % len;
        } else if (x >= len) {
            return x % len;
        }
        return x;
    }


    private void resize(int newSize) {
        T[] temp1 = (T []) new Object[size()];
        T[] temp2 = (T []) new Object[newSize];

        int sSrcPos = loopPos(startIndex, repo.length);
        int eSrcPos = loopPos(endIndex - 1, repo.length);

        int sDesPos = loopPos(startIndex, newSize);
        int eDesPos = loopPos(endIndex - 1, newSize);

        if (eSrcPos < sSrcPos) {
            System.arraycopy(repo, sSrcPos, temp1, 0, repo.length - sSrcPos);
            System.arraycopy(repo, 0, temp1, repo.length - sSrcPos, eSrcPos + 1);
        } else {
            System.arraycopy(repo, sSrcPos, temp1, 0, eSrcPos - sSrcPos + 1);
        }

        if (eDesPos < sDesPos) {
            System.arraycopy(temp1, 0, temp2, sDesPos, newSize - sDesPos);
            System.arraycopy(temp1, newSize - sDesPos, temp2, 0, eDesPos + 1);
        } else {
            System.arraycopy(temp1, 0, temp2, sDesPos, eDesPos - sDesPos + 1);
        }
        repo = temp2;
    }


    @Override
    public void addLast(T x) {
        if (size() == repo.length) {
            resize(repo.length * 2);
        }
        repo[loopPos(endIndex, repo.length)] = x;
        endIndex = endIndex + 1;
    }


    @Override
    public void addFirst(T x) {
        if (size()  == repo.length) {
            resize(repo.length * 2);
        }
        repo[loopPos(startIndex - 1, repo.length)] = x;
        startIndex = startIndex - 1;
    }


    @Override
    public boolean isEmpty() {
        return endIndex - startIndex == 0;
    }


    @Override
    public int size() {
        return endIndex - startIndex;
    }


    @Override
    public void printDeque() {
        for (int i = startIndex; i < endIndex; i = i + 1) {
            System.out.print(repo[loopPos(i, repo.length)]);
            System.out.print(" ");
        }
    }


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (repo.length / size() > 4 && repo.length > 8) {
            resize(repo.length / 2 + 1);
        }
        T res = repo[loopPos(startIndex, repo.length)];
        repo[loopPos(startIndex, repo.length)] = null;
        startIndex = startIndex + 1;
        return res;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (repo.length / size() > 4 && repo.length > 8) {
            resize(repo.length / 2 + 1);
        }
        T res = repo[loopPos(endIndex - 1, repo.length)];
        repo[loopPos(endIndex - 1, repo.length)] = null;
        endIndex = endIndex - 1;
        return res;
    }


    @Override
    public T get(int index) {
        if (index >= size()) {
            return null;
        } else if (index < 0) {
            return null;
        }
        return repo[loopPos(startIndex + index, repo.length)];
    }


}

