# Embedded Linux with Yocto Project

This project was created as part of my learning journey to understand **Embedded Linux** using **Yocto**. The project is **built and tested for the Raspberry Pi 3 Model B+** board.

## Step-by-Step Approach

1. **Yocto Project - Minimal Image Creation**
   - Set up a minimal Yocto image.
   
2. **Setting up the Poky Project (Scarthgap Branch)**
   - Clone the Poky project using the following command:
     ```bash
     git clone git://git.yoctoproject.org/poky -b scarthgap
     ```

3. **Cloning Additional Layers**
   - Visit the following link to pick additional layers:  
     [OpenEmbedded Layer Index](https://layers.openembedded.org/layerindex/branch/master/layers/)
   
4. **Building the Image**
   - Run the following command to build the minimal image:
     ```bash
     bitbake core-image-minimal
     ```

5. **Serial Communication Setup**
   - Set up serial communication to observe the kernel booting process.

6. **SSH Enabled for Module Transfer**
   - Enabled SSH to transfer modules to the target device.

7. **Wi-Fi Enabled**
   - Configured Wi-Fi support for network connectivity.

8. **Meta Custom Layer Creation**
   - Created a custom layer to integrate smaller projects.

### Integrated Projects:

1. **RaspberryPi Utilities**
   - Integrated **Raspberry Pi utilities** into the Yocto build and added them to the install image append.

2. **Small Networking Project**
   - Created recipes for networking functionalities:
     - **Client Module**: Reads keypresses from the keyboard and transfers the data to the server module via **UDP** socket programming.
     - **Server Module**: Receives the data from the client module and transfers it to the display module via **UDP** socket programming.
     - **Display Module**: Prints the received data to the console.

This project serves as a practical learning exercise for working with Embedded Linux and Yocto, covering various essential steps such as creating images, setting up communication, and integrating small networking projects.
