public class ArrayDeque<T>{
    private T[] repo;
    private int start_index;
    private int end_index;


    public ArrayDeque(){
        repo = (T []) new Object[8];
        end_index = 0;
        start_index = 0;
    }


    private int loop_pos(int x, int len){
        if (x < 0){
            if ((0 - x)%len == 0){
                return 0;
            }
            return len - (- x) % len ;
        }
        else if(x >= len){
            return x % len;
        }
        return x;
    }


    private void resize(int new_size){
        T[] temp1 = (T []) new Object[size()];
        T[] temp2 = (T []) new Object[new_size];

        int s_src_pos = loop_pos(start_index, repo.length);
        int e_src_pos = loop_pos(end_index - 1, repo.length);

        int s_des_pos = loop_pos(start_index, new_size);
        int e_des_pos = loop_pos(end_index - 1, new_size);

        if (e_src_pos < s_src_pos){
            System.arraycopy(repo, s_src_pos, temp1, 0, repo.length - s_src_pos);
            System.arraycopy(repo, 0, temp1, repo.length - s_src_pos,e_src_pos + 1);
        }
        else{
            System.arraycopy(repo, s_src_pos, temp1, 0, e_src_pos - s_src_pos + 1);
        }

        if (e_des_pos < s_des_pos){
            System.arraycopy(temp1, 0, temp2, s_des_pos, new_size - s_des_pos);
            System.arraycopy(temp1, new_size - s_des_pos, temp2, 0 ,e_des_pos + 1);
        }
        else{
            System.arraycopy(temp1, 0, temp2, s_des_pos, e_des_pos - s_des_pos + 1);
        }
        repo = temp2;
    }


    public void addLast(T x){
        if (size() == repo.length){
            resize(repo.length * 2);
        }
        repo[loop_pos(end_index, repo.length)] = x;
        end_index = end_index + 1;
    }


    public void addFirst(T x){
        if (size()  == repo.length){
            resize(repo.length * 2);
        }
        repo[loop_pos(start_index - 1, repo.length)] = x;
        start_index = start_index - 1;
    }


    public boolean isEmpty(){
        return end_index - start_index == 0;
    }


    public int size(){
        return end_index - start_index;
    }


    public void printDeque(){
        for (int i = start_index; i < end_index; i = i + 1){
            System.out.print(repo[loop_pos(i, repo.length)]);
            System.out.print(" ");
        }
    }


    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        if (repo.length / size() > 4 && repo.length > 8){
            resize(repo.length / 2 + 1);
        }
        T res = repo[loop_pos(start_index, repo.length)];
        repo[loop_pos(start_index, repo.length)] = null;
        start_index = start_index + 1;
        return res;
    }


    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        if (repo.length / size() > 4 && repo.length > 8){
            resize(repo.length / 2 + 1);
        }
        T res = repo[loop_pos(end_index - 1, repo.length)];
        repo[loop_pos(end_index - 1, repo.length)] = null;
        end_index = end_index -1;
        return res;
    }

    public T get(int index){
        if (index >= size()){
            return null;
        }
        else if(index < 0){
            return null;
        }
        return repo[loop_pos(start_index + index, repo.length)];
    }


}

