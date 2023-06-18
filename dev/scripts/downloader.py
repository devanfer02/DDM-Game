from pytube import YouTube

def download(url) :
    youtube = YouTube(url, use_oauth=True, allow_oauth_cache=True)
    youtube = youtube.streams.get_highest_resolution()
    try :
        youtube.download()
    except :
        print('Error')
    print('Dapet')

url = str(input("Masukkan youtube url : "))
download(url)