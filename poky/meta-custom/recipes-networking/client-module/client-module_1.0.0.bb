SUMMARY = "client module for error logging"
DESCRIPTION = "A UDP client that detects the keypress and transmits UDP packet to server module."
LICENSE = "CLOSED"
SRC_URI = "file://client-module.cpp"


DEPENDS = "virtual/libc"

S = "${WORKDIR}"

# Adding CXXFLAGS globally to ensure linker options are applied
CXXFLAGS += "-Wl,--hash-style=gnu"

# Compile the server module
do_compile() {
    ${CXX} ${S}/client-module.cpp -o ${S}/client-module ${CXXFLAGS}
}

# Install the client binary to the target filesystem
do_install() {
    # Install the binary to the appropriate directory
    install -d ${D}${bindir}
    install -m 0755 ${S}/client-module ${D}${bindir}
}

# Package the binary
FILES:${PN} = "${bindir}/client-module"

