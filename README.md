# Operating-System-Memory-Page-Replacement-Simulation
This program simulates a system that uses paging with virtual memory.
Clock and Least recently Used (LRU) are applied for page replacement, where each process can have a maximum 50 pages. When a page fault handling occurs, the interrupt routine will handle thw fault by placing an I/O requesto into a queue, which will later be processed by the I/O controller to bring the page into main memory. (Swapping in a page takes 6 units of time.)
The system is to use a Round Robin short-term scheduling algorithm with time a quantum of Q.
This project compares the performance differences between LRU ad clock page replacement algorithms for the fixed allocation with local replacement strategy.
