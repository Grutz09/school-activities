#include <iostream>
#include <vector>
#include <ctime>
#include <cstdlib>
#include <algorithm>
using namespace std;

// function to display heap
void displayHeap(const vector<int> &heap)
{
    cout << "Your current party (Max Heap): [ ";
    for (int power : heap)
    {
        cout << power << " ";
    }
    cout << "]" << endl;
}

// function to insert heroes power into the heap
void insertAdventurer(vector<int> &heap, int power)
{
    heap.push_back(power);
    int i = heap.size() - 1;

    while (i != 0 && heap[(i - 1) / 2] < heap[i])
    {
        swap(heap[i], heap[(i - 1) / 2]);
        i = (i - 1) / 2;
    }

    cout << "Adventurer with Power Level " << power << " joins your party!" << endl;
    displayHeap(heap);
}

// function to eliminate strongest heroes
void eliminateStrongest(vector<int> &heap)
{
    if (heap.empty())
    {
        cout << "No adventurers left in your team!" << endl;
        return;
    }

    int strongest = heap[0];
    heap[0] = heap.back();
    heap.pop_back();

    int i = 0;
    while (2 * i + 1 < heap.size())
    {
        int largest = i;       // Initialize largest as root
        int left = 2 * i + 1;  // Left child index
        int right = 2 * i + 2; // Right child index

        if (left < heap.size() && heap[left] > heap[largest])
        {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < heap.size() && heap[right] > heap[largest])
        {
            largest = right;
        }

        if (largest == i)
        break;

        swap(heap[i], heap[strongest]);
        i = largest;
    }

    cout << "Adventurer with Power Level " << strongest << " has gone to the battle and left your party." << endl;
    displayHeap(heap);
}

// function to heapify a random array
void heapifyAdventurers(vector<int> &adventurers)
{
    int n = adventurers.size();

    for (int i = n / 2 - 1; i >= 0; i--)
    {
        int current = i;

        while (2 * current + 1 < n)
        {
            int largest = current; // Initialize largest as root
            int left = 2 * i + 1;  // Left child index
            int right = 2 * i + 2; // Right child index

            if (left < n && adventurers[left] > adventurers[largest])
            {
                largest = left;
            }

            // If right child is larger than largest so far
            if (right < n && adventurers[right] > adventurers[largest])
            {
                largest = right;
            }

            if (largest == current) break;

            swap(adventurers[current], adventurers[largest]);
            current = largest;
        }
    }

    cout << "Random Adventurers have been recruited from the Guild and organized into your party!" << endl;
    displayHeap(adventurers);
}

int main()
{
    vector<int> party;
    int choice;

    srand(time(0));

    cout << "====== 🛡️  Welcome to Adventurer's Guild: Battle of Power! ⚔️  ======" << endl;
    do
    {
        cout << "\n Choose an action⚔️:" << endl
             << "1. Recruit a new Adventurer from the Guild (insert)" << endl
             << "2. Send the strongest adventurer to battle (remove)" << endl
             << "3. View your current party" << endl
             << "4. Reruit random adventurers and organize them (heapify)" << endl
             << "5. Exit Game" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice)
        {
        case 1:
            int power;
            cout << "Enter the hero's power level (integer): ";
            cin >> power;
            insertAdventurer(party, power);
            break;
        case 2:
            eliminateStrongest(party);
            break;

        case 3:
            if (party.empty())
            {
                cout << "Your party is currently empty";
            }else{
                displayHeap(party);
            }
            break;

        case 4:
        {
            vector<int> randomAdventurer =
            {rand() % 100, rand() % 100 + 1, rand() % 100 + 1, rand() % 100 + 1, rand() % 100 + 1};

            heapifyAdventurers(randomAdventurer);
            party = randomAdventurer;
        }
        break;

        case 5:
            cout << "Thank You for playinig Adventurer's Guild: Battle of Power! See you next time!" << endl;
            break;
        default:
            cout << "Invalid choice. Please Try Again" << endl;
            break;
        }
    } while (choice != 5);

    return 0;
}
