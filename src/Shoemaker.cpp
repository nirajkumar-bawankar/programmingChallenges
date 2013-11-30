#include <algorithm>
#include <iostream>
#include <vector>

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=downloadproblem&probid=110405&format=html
 *
 * The following is a solution for the above problem.
 */
using namespace std;

class ShoeOrderInfo {
private:
  int _index;
  int _timeInDays;
  int _paymentPerStartLateDay;

public:
  ShoeOrderInfo(int index, int timeInDays, int paymentPerStartLateDay) :
      _index(index), _timeInDays(timeInDays), _paymentPerStartLateDay(
          paymentPerStartLateDay) {
  }

  bool operator <(const ShoeOrderInfo & shoeOrderInfo) const {
    int cost1 = _timeInDays * shoeOrderInfo._paymentPerStartLateDay;
    int cost2 = shoeOrderInfo._timeInDays * _paymentPerStartLateDay;

    return cost1 < cost2 ?
        true : (cost1 == cost2 ? _index < shoeOrderInfo._index : false);
  }

  int getIndex() const {
    return _index;
  }

  static bool compareShoeOrderInfos(const ShoeOrderInfo * shoeOrderInfo1,
      const ShoeOrderInfo * shoeOrderInfo2) {
    return *shoeOrderInfo1 < *shoeOrderInfo2;
  }
};

int main() {
  int numberOfTestCases = 0;
  cin >> numberOfTestCases;
  for (int i = 0; i < numberOfTestCases; i++) {
    int numberOfJobs;
    cin >> numberOfJobs;

    vector<ShoeOrderInfo> shoeOrderInfos;

    vector<ShoeOrderInfo*> listOfShoeOrdersToBeSorted;

    shoeOrderInfos.reserve(numberOfJobs);
    listOfShoeOrdersToBeSorted.reserve(numberOfJobs);

    for (int currentJob = 1; currentJob <= numberOfJobs; currentJob++) {
      int currentJobTimeInDaysToComplete;
      int currentJobPaymentPerStartLateDay;

      cin >> currentJobTimeInDaysToComplete
          >> currentJobPaymentPerStartLateDay;

      shoeOrderInfos.push_back(
          ShoeOrderInfo(currentJob, currentJobTimeInDaysToComplete,
              currentJobPaymentPerStartLateDay));

      listOfShoeOrdersToBeSorted.push_back(&shoeOrderInfos.back());
    }

    sort(listOfShoeOrdersToBeSorted.begin(),
        listOfShoeOrdersToBeSorted.end(),
        ShoeOrderInfo::compareShoeOrderInfos);

    if (i) {
      cout << endl;
    }

    for (int currentJob = 0; currentJob < numberOfJobs; currentJob++) {
      if (currentJob) {
        cout << ' ';
      }
      cout << listOfShoeOrdersToBeSorted[currentJob]->getIndex();
    }

    cout << endl;
  }
  return 0;
}
