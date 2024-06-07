# Magnet Extractor

Magnet extractor is a simple utility that can help you extract torrent magnet links from URLs or full html code.

Sometimes torrent sites make it difficult to get at the magnet. With this utility, you can pass in the URL of the page that has the magnet, or you can use your browsers developer mode to copy the full html code into your clipboard then run this utility and the magnet link will be placed in your clipboard.

## Installation

Download the appropriate binary [from here](https://github.com/EasyG0ing1/magnetextractor/releases/latest) and place it in a folder
that is in your PATH environment variable.

## Usage

Its real simple. If you have the html OR a URL in your clipboard you just run the program:

```bash
magnet
```

You can also pass the URL in as an argument:

```bash
magnet https://someserver.com/some/link
```

If you need the magnet to be saved to a file, like for batch processing, do it like this:
```bash
magnet out >myFile.txt
magnet out https://someserver.com/some/url >myFile.txt
```

You can also run
```bash
magnet --help
magnet /?
```
To get these instructions.

If you have any problems or would like something changed with the program, create an issue. If you wish to contribute, feel free to submit a pull request.

## Version History

* 1.0.0
    * Initial Release
