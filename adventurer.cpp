#include <iostream>
#include <vector>
#include <ctime>
#include <cstdlib>
#include <algorithm>

using namespace std;

// Function to display the heap
void displayHeap(const vector<int> &heap)
{
    cout << "Current party (Max Heap): [ ";
    for (int power : heap)
    {
        cout << power << " ";
    }
    cout << "]\n";
}

// Function to insert adventurer's power into the heap
void insertAdventurer(vector<int> &heap, int power)
{
    heap.push_back(power);
    push_heap(heap.begin(), heap.end()); // use built-in heapify function
    cout << "Adventurer with Power Level " << power << " joins your party!\n";
    displayHeap(heap);
}

// Function to eliminate strongest adventurer
void eliminateStrongest(vector<int> &heap)
{
    if (heap.empty())
    {
        cout << "No adventurers left in your team!\n";
        return;
    }

    pop_heap(heap.begin(), heap.end()); // remove top element (strongest)
    cout << "Adventurer with Power Level " << heap.back() << " has gone to battle and left your party.\n";
    heap.pop_back(); // Remove the last element after pop_heap
    displayHeap(heap);
}

// Function to heapify a random array of adventurers
void heapifyAdventurers(vector<int> &adventurers)
{
    make_heap(adventurers.begin(), adventurers.end()); // Use built-in function to heapify
    cout << "Random adventurers have been recruited and organized into your party!\n";
    displayHeap(adventurers);
}

int main()
{
    vector<int> party;
    int choice;
    srand(time(0));

    cout << "====== Welcome to Adventurer's Guild: Battle of Power! ======\n";
    do
    {
        cout << "\nChoose an action:\n"
             << "1. Recruit a new Adventurer (insert)\n"
             << "2. Send the strongest adventurer to battle (remove)\n"
             << "3. View your current party\n"
             << "4. Recruit random adventurers and organize them (heapify)\n"
             << "5. Exit Game\n";
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
                cout << "Your party is currently empty.\n";
            }
            else
            {
                displayHeap(party);
            }
            break;

        case 4:
        {
            vector<int> randomAdventurers = {
                rand() % 100, rand() % 100 + 1, rand() % 100 + 1,
                rand() % 100 + 1, rand() % 100 + 1};
            heapifyAdventurers(randomAdventurers);
            party = randomAdventurers; // Replace party with new adventurers
        }
        break;

        case 5:
            cout << "Thank you for playing Heap Adventure! See you next time!\n";
            break;

        default:
            cout << "Invalid choice. Please try again.\n";
            break;
        }
    } while (choice != 5);

    return 0;
}