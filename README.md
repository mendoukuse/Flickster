# Project 1 - Flickster

Flickster shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: 3-6 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Overview (Portrait mode)
  * [x] Title, Backdrop Image, Overview (Landscape mode)

The following **optional** features are implemented:

* [ ] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.

The following **bonus** features are implemented:

* [x] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [ ] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [ ] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [ ] Overlay a play icon for videos that can be played.
    * [ ] More popular movies should start a separate activity that plays the video immediately.
    * [ ] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [x] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [x] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [x] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries.

The following **additional** features are implemented:

* [x] List anything else that you can get done to improve the app functionality!
* [x] Cleaned up UI a bit and removed app top bar 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/mendoukuse/Flickster/blob/master/images/Flickster%20(Landscape%20View).gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='https://github.com/mendoukuse/Flickster/blob/master/images/Flickster%20(Portrait%20View).gif' title='Video Walkthrough 2' width='' alt='Video Walkthrough 2' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Challenges and Questions 
- I couldn't quite figure out how to get the movie poster images to resize correctly in a vertical view. Calling .fit() would cause my images to disappear and I wasn't sure if that was because the images needed explicit sizes or not. 
- Is the OkHttp Client a Singleton by default?
- What is the best way to create placeholder graphics. I figured out how to use colored shapes as image placeholders but I wanted to make a nice placeholder and couldn't figure out how. 


## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [OkHTTP](https://github.com/square/okhttp) - An HTTP+HTTP/2 client for Android and Java applications

## License

    Copyright [2017] [christine-nguyen]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
