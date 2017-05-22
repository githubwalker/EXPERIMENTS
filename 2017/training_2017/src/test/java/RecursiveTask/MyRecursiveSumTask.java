package RecursiveTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by andrew on 23.05.2017.
 */
class MyRecursiveSumTask extends RecursiveTask<Integer>
{
    private List<Integer> _array;
    private int _leftBound;
    private int _rightBound;
    final int _treshold = 10000;

    private int size()
    {
        return _rightBound - _leftBound;
    }

    private MyRecursiveSumTask(List<Integer> myarray, int leftBound, int rightBound)
    {
        _array = myarray;
        _leftBound = leftBound;
        _rightBound = rightBound;
    }

    MyRecursiveSumTask(List<Integer> myarray)
    {
        this(myarray, 0, myarray.size());
    }


    @Override
    protected Integer compute()
    {
        if ( this.size() > _treshold)
        {
            int middle = _leftBound + ((_rightBound - _leftBound) >> 1);

            MyRecursiveSumTask taskLeft = new MyRecursiveSumTask(_array, _leftBound, middle );
            taskLeft.fork();
            MyRecursiveSumTask taskRight = new MyRecursiveSumTask(_array, middle, _rightBound );
            return taskRight.compute() + taskLeft.join();
        }
        else
        {
            int sum = 0;
            for(int i = _leftBound; i < _rightBound; i ++ )
                sum += _array.get(i);
            return sum;
        }
    }
}
